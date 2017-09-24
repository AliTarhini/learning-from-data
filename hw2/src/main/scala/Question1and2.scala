import scala.util.Random

object Question1and2 {

  def run(): Unit = {
    val nuSum = (0 until 100000).foldLeft((0d, 0d, 0d)){ case(sum, _) =>
      val t = testOneTime()
      (sum._1 + t._1, sum._2 + t._2, sum._3 + t._3)
    }

    val nu1 = nuSum._1 / 100000
    val nuRand = nuSum._2 / 100000
    val nuMin = nuSum._3 / 100000

    println(s"Nu_1: $nu1, Nu_rand: $nuRand, Nu_min: $nuMin")
    // Nu_1: 0.49994599999999717, Nu_rand: 0.49969499999999833, Nu_min: 0.0375579999999768
  }

  private def testOneTime(): (Double, Double, Double) = {
    val coins = (0 until 1000).map{ _ => (0 until 10).map{ _ => flipCoin() } }
    val nu1 = calcNu(coins.head)
    val nuRand = calcNu(coins(Random.nextInt(1000)))
    val nuMin = coins.map(f => calcNu(f)).min

    (nu1, nuRand, nuMin)
  }

  private def flipCoin(): Boolean = {
    Random.nextInt(2) == 1
  }

  private def calcNu(flipResults: Seq[Boolean]): Double = {
    flipResults.count(_ == true).toDouble / flipResults.size
  }

}

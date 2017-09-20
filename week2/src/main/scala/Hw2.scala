/**
  * Run a computer simulation for flipping 1,000 virtual fair coins. Flip each coin inde-
    pendently 10 times. Focus on 3 coins as follows: c 1 is the first coin flipped, c rand is a
    coin chosen randomly from the 1,000, and c min is the coin which had the minimum
    frequency of heads (pick the earlier one in case of a tie). Let ν 1 , ν rand , and ν min be
    the fraction of heads obtained for the 3 respective coins out of the 10 tosses.
    Run the experiment 100,000 times in order to get a full distribution of ν 1 , ν rand , and
    ν min (note that c rand and c min will change from run to run).

    1. The average value of ν min is closest to:
    [a] 0
    [b] 0.01
    [c] 0.1
    [d] 0.5
    [e] 0.67

    2. Which coin(s) has a distribution of ν that satisfies Hoeffding’s Inequality?
    [a] c 1 only
    [b] c rand only
    [c] c min only
    [d] c 1 and c rand
    [e] c min and c rand
  */

// nu 1, nu rand, nu min 对应的 mu 1, mu rand, mu min 应该是 0.5, 0.5, 0

object Hw2 {

  private val r = new scala.util.Random

  def main(args: Array[String]) = {
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
    val nuRand = calcNu(coins(r.nextInt(1000)))
    val nuMin = coins.map(f => calcNu(f)).min

    (nu1, nuRand, nuMin)
  }

  private def flipCoin(): Boolean = {
    r.nextInt(2) == 1
  }

  private def calcNu(flipResults: Seq[Boolean]): Double = {
    flipResults.count(_ == true).toDouble / flipResults.size
  }

}

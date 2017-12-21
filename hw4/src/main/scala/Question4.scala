import scala.util.Random

object Question4 {

  def run(): Unit = {
    val example1 = (0 until 10000).map { _ =>
      val x = -1 + Random.nextDouble()
      val y = f(x)
      (x, y)
    }
    val exmpale2 = (0 until 10000).map { _ =>
      val x = -1 + Random.nextDouble()
      val y = f(x)
      (x, y)
    }

//    val a = example1.zip(exmpale2).map { case((x1, y1), (x2, y2)) => (y1 * x2 + x1 * y2) / (2 * x1 * x2)}.sum / 10000
    val a = example1.zip(exmpale2).map { case((x1, y1), (x2, y2)) => (y1 + y2) / (x1 + x2)}.sum / 10000

//    val a = example1.map{ case(x, y) => if (x.abs < 0.00000001) 0 else y / x}.sum / 10000
    println(a)
  }

  def f(x: Double): Double = Math.sin(Math.PI * x)

}

import scala.util._

/**
  * The Perceptron Learning Algorithm
In this problem, you will create your own target function f and data set D to see
how the Perceptron Learning Algorithm works. Take d = 2 so you can visualize the
problem, and assume X = [−1, 1] × [−1, 1] with uniform probability of picking each
x ∈ X .
In each run, choose a random line in the plane as your target function f (do this by
taking two random, uniformly distributed points in [−1, 1] × [−1, 1] and taking the
line passing through them), where one side of the line maps to +1 and the other maps
to −1. Choose the inputs x n of the data set as random points (uniformly in X ), and
evaluate the target function on each x n to get the corresponding output y n .
Now, in each run, use the Perceptron Learning Algorithm to find g. Start the PLA
with the weight vector w being all zeros, and at each iteration have the algorithm
choose a point randomly from the set of misclassified points. We are interested in
two quantities: the number of iterations that PLA takes to converge to g, and the
disagreement between f and g which is P[f (x) != g(x)] (the probability that f and g
will disagree on their classification of a random point). You can either calculate this
probability exactly, or approximate it by generating a sufficiently large, separate set
of points to estimate it.
In order to get a reliable estimate for these two quantities, you should repeat the
experiment for 1000 runs (each run as specified above) and take the average over
these runs.
7. Take N = 10. How many iterations does it take on average for the PLA to
converge for N = 10 training points? Pick the value closest to your results
(again, ‘closest’ means: |your answer − given option| is closest to 0).
[a] 1
[b] 15
[c] 300
[d] 5000
[e] 10000
8. Which of the following is closest to P[f (x) != g(x)] for N = 10?
[a] 0.001
[b] 0.01
[c] 0.1
[d] 0.5
[e] 0.8
9. Now, try N = 100. How many iterations does it take on average for the PLA
to converge for N = 100 training points? Pick the value closest to your results.
[a] 50
[b] 100
[c] 500
[d] 1000
[e] 5000
10. Which of the following is closest to P[f (x) != g(x)] for N = 100?
[a] 0.001
[b] 0.01
[c] 0.1
[d] 0.5
[e] 0.8
  */

// Got trouble in getting f by 2 points

object Hw1 {

  private val r = new Random

  // It wrong!!
  def main(args: Array[String]) = {
    val n = 10
    val error = (0 until 1000).foldLeft(0d){ case(s, _) =>
      val f = generateLine()
      val points = generatePoints(f, n)
      var g = (0d, 0d)
      points.foreach { p =>
        g = plaOneTime(g, p)
      }

      s + calcErrorProbablity(f, g)
    }

    println(error /  1000)
  }

  private def generateLine(): (Double, Double, Double) = {
    val x11 = -1 + r.nextDouble()
    val x12 = -1 + r.nextDouble()
    val x21 = -1 + r.nextDouble()
    val x22 = -1 + r.nextDouble()

    val y1 = -1 + r.nextDouble()
    val y2 = -1 + r.nextDouble()

    (x11 + x21, x12 + x22, y1 + y2)
  }

  private def generatePoints(f: (Double, Double, Double), n: Int): List[(Double, Double, Int)] = {
    (0 until n).map { _ =>
      val x1 = -1 + r.nextDouble()
      val x2 = -1 + r.nextDouble()

      val res = x1 * f._1 + x2 * f._2 + f._3
      val y = if (res > 0) 1 else -1

      (x1, x2, y)
    }.toList
  }

  // w0 == 0, so g only has w1 & w2 for x1 & x2
  private def plaOneTime(g: (Double, Double), x: (Double, Double, Int)): (Double, Double) = {
    val res = g._1 * x._1 + g._2 * x._2
    val y = if (res > 0) 1 else -1

    if (y == x._3) {
      g
    } else {
      (g._1 + y * x._1, g._2 + x._2)
    }
  }

  private def isMatched(g: (Double, Double), points: List[(Double, Double, Int)]): Boolean = {
    points.exists { p => !isMatched(g, p) }
  }

  private def isMatched(g: (Double, Double), point: (Double, Double, Int)): Boolean = {
    val gRes = point._1 * g._1 + point._2 * g._2
    gRes.signum == point._3.signum
  }

  private def calcErrorProbablity(f: (Double, Double, Double), g: (Double, Double)): Double = {
    val errorSize = (0 until 10000).foldLeft(0) { case(s, _) =>
      val x1 = -1 + r.nextDouble()
      val x2 = -1 + r.nextDouble()

      val fRes = x1 * f._1 + x2 * f._2 + f._3
      val gRes = x1 * g._1 + x2 * g._2

      if (fRes.signum == gRes.signum) s else s + 1
    }

    errorSize / 10000
  }


}

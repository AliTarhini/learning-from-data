import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.mllib.linalg.Vectors

import scala.util.Random
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.sql.{SQLContext, SparkSession}

object Question5to7 {

  def run(): Unit = {
    val conf = new SparkConf().setAppName("hw2").setMaster("local")
    val sc=new SparkContext(conf)
    val sqc = SparkSession.builder()
      .appName("SparkSql hive test")
      .getOrCreate()

    val n = 10
//    val (iter, error) =
    (0 until 1000).foreach { _ =>
      val f = generateLine()
      val points = generatePoints(f, n)
      val pointsRdd = sc.parallelize(points)
      val pointsDF = sqc.createDataFrame(pointsRdd).toDF("x1", "x2", "y")

      val assembler = new VectorAssembler().setInputCols(Array("x1", "x2")).setOutputCol("features")
      val vecDF = assembler.transform(pointsDF)

      val lr1 = new LinearRegression()
      val lr2 = lr1.setFeaturesCol("features").setLabelCol("y").setFitIntercept(true)

      val lr3 = lr2.setMaxIter(100).setRegParam(0.3).setElasticNetParam(0.8)
      val lr = lr3

      val lrModel = lr.fit(vecDF)

      val verifyPoints = (0 until 10000).map { _ =>
        val x1 = -1 + Random.nextDouble()
        val x2 = -1 + Random.nextDouble()

        (x1, x2, f(x1, x2))
      }
      val verifyPointsRdd = sc.parallelize(verifyPoints)
      val verifyPointsDF = sqc.createDataFrame(verifyPointsRdd).toDF("x1", "x2", "y")
      val verifyVecDF = assembler.transform(verifyPointsDF)

      val predictions = lrModel.transform(verifyVecDF)
      println(predictions)

    }

//    println("Result: " + iter / 1000 + ", " + error /  1000)
  }

  private def generateLine(): (Double, Double) => Int= {
    val a1 = -1 + Random.nextDouble()
    val a2 = -1 + Random.nextDouble()
    val b1 = -1 + Random.nextDouble()
    val b2 = -1 + Random.nextDouble()

    val y1 = -1 + Random.nextDouble()
    val y2 = -1 + Random.nextDouble()

    (x: Double, y: Double) => {
      // My line:
      val myLine = a2 * x - b2 * x + a1 * b2 - a2 * b1 - a1 * y + b1 * y
      // better line
      val betterLine = (x - a1) / (a2 - a1) - (y - b1) / (b2 - b1)
      if (betterLine >= 0) {
        1
      } else {
        -1
      }
    }
  }

  private def generatePoints(f: (Double, Double) => Int, n: Int): List[(Double, Double, Int)] = {
    (0 until n).map { _ =>
      val x1 = -1 + Random.nextDouble()
      val x2 = -1 + Random.nextDouble()

      (x1, x2, f(x1, x2))
    }.toList
  }

}

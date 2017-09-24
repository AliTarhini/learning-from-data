scalaVersion := "2.11.8"

libraryDependencies ++= {
  Seq(
    "org.apache.spark" % "spark-core_2.11" % "2.2.0",
    "org.apache.spark" % "spark-mllib_2.11" % "2.2.0",
    "org.scalanlp" % "breeze_2.11" % "0.13.2"
  )
}

name := "spark-test"

version := "0.0.x"

libraryDependencies ++= {
  Seq(
    "org.apache.spark" % "spark-core_2.11" % "2.2.0" % "provided"
  )
}

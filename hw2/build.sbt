libraryDependencies ++= {
  Seq(
    "org.apache.spark" % "spark-core_2.11" % "2.2.0" % "provided",
    "org.apache.spark" % "spark-mllib_2.11" % "2.2.0" % "provided",
    "org.scalanlp" % "breeze_2.11" % "0.13.2"
  )
}

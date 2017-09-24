name := "learning-from-data"

version := "0.0.x"

libraryDependencies ++= {
  Seq(
    "org.apache.spark" % "spark-core_2.11" % "2.2.0" % "provided",
    "org.apache.spark" % "spark-mllib_2.11" % "2.2.0" % "provided"

  )
}

lazy val hw1 = project.in(file("hw1"))
lazy val hw2 = project.in(file("hw2"))

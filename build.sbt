name := "learning-from-data"

version := "0.0.x"

libraryDependencies ++= {
  Seq(
    "org.apache.spark" % "spark-core_2.11" % "2.2.0" % "provided"
  )
}

lazy val hw2 =  project.in(file("hw2"))

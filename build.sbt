name := "learning-from-data"

version := "0.0.x"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  Seq(
    "org.apache.spark" %% "spark-core" % "2.2.1",
    "org.apache.spark" %% "spark-mllib" % "2.2.1" % "provided"
  )
}

lazy val hw1 = project.in(file("hw1"))
lazy val hw2 = project.in(file("hw2"))
lazy val hw4 = project.in(file("hw4"))

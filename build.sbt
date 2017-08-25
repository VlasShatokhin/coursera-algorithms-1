name := "algorithms"

version := "1.0"

scalaVersion := "2.12.2"

resolvers += Resolver.jcenterRepo
libraryDependencies ++= Seq(
  "edu.princeton.cs" % "algs4" % "1.0.3",
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)

name := "conceptTagger"

version := "1.0"

//scalaVersion := "2.12.2"
scalaVersion := "2.11.8"

logBuffered in Test := false

fork in Test := true

libraryDependencies ++= Dependencies.logging ++ Dependencies.test ++ Dependencies.akka ++ Dependencies.nlp


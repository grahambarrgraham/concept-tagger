name := "conceptTagger"

version := "1.0"

//scalaVersion := "2.12.2"
scalaVersion := "2.11.8"

logBuffered in Test := false

fork in Test := true

parallelExecution in Test := false

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Dependencies.logging ++ Dependencies.test ++ Dependencies.akka ++ Dependencies.nlp

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

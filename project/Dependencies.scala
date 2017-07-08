import sbt._

object Versions {
  val akka = "2.4.17"
  val scalaTest = "3.0.1"
  val logback = "1.1.3"
}

object Modules {
  final val Logback : ModuleID = "ch.qos.logback"  %  "logback-classic"  % Versions.logback
  final val ScalaTest : ModuleID = "org.scalatest" %% "scalatest" % Versions.scalaTest % Test
  final val Scalactic: ModuleID = "org.scalactic"   %% "scalactic" % Versions.scalaTest
  final val ScalaMock: ModuleID = "org.scalamock" %% "scalamock-scalatest-support"  % "3.5.0" % Test
  final val ScalaLogging: ModuleID = "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
  final val ScalaMeter: ModuleID = "com.storm-enroute" %% "scalameter" % "0.9-SNAPSHOT" % Test
}

object Dependencies {

  final val akka: Seq[ModuleID] = Seq(
    "com.typesafe.akka" %% "akka-actor"           % Versions.akka,
    "com.typesafe.akka" %% "akka-persistence"     % Versions.akka,
    "com.typesafe.akka" %% "akka-testkit"         % Versions.akka      % "test"
  )

  final val logging: Seq[ModuleID] = Seq(
    Modules.Logback,
    Modules.ScalaLogging
  )

  final val test: Seq[ModuleID] = Seq(
    Modules.ScalaTest,
    Modules.ScalaMock
  )

  final val nlp: Seq[ModuleID] = Seq(
    "org.apache.opennlp" % "opennlp-tools" % "1.8.0"
  )

  final val gatling: Seq[ModuleID] = Seq(
    "io.gatling.highcharts"   % "gatling-charts-highcharts" % "2.2.3" % "test,it",
    "io.gatling"              % "gatling-test-framework"    % "2.2.3" % "test,it"
  )
}

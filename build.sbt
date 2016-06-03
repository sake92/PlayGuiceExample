name := """PlayGuice"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.scalatestplus" % "play_2.11" % "1.4.0"
)

javaOptions in Test += "-Dconfig.resource=" + System.getProperty("config.resource", "application.test.conf")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

routesGenerator := InjectedRoutesGenerator

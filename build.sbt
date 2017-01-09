name := """PlayGuice"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(  
  "com.typesafe.play" %% "play-slick" % "1.1.1",
  "com.h2database" % "h2" % "1.4.187",
  "org.scalatestplus" %% "play" % "1.4.0" % "test"
)

javaOptions in Test += "-Dconfig.resource=" + System.getProperty("config.resource", "application.test.conf")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

routesGenerator := InjectedRoutesGenerator

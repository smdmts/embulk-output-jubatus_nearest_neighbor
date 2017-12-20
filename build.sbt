enablePlugins(ScalafmtPlugin)

lazy val root = (project in file("."))

resolvers += Resolver.jcenterRepo
resolvers += Resolver.sonatypeRepo("releases")
resolvers += "velvia maven" at "http://dl.bintray.com/velvia/maven"
resolvers += "Jubatus Repository for Maven" at "http://download.jubat.us/maven"
resolvers += "msgpack-rpc" at "http://msgpack.org/maven2/"

scalaVersion := "2.12.4"

scalafmtOnCompile in ThisBuild := true
scalafmtTestOnCompile in ThisBuild := true

lazy val circeVersion = "0.8.0"
libraryDependencies ++= Seq(
  "org.jruby" % "jruby-complete" % "1.6.5",
  "org.embulk" % "embulk-core" % "0.8.39",
  "us.jubat" % "jubatus" % "1.0.+",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % Test,
  "org.scalatest" %% "scalatest" % "3.0.1" % Test,
  "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test,
  "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.5" % Test
)

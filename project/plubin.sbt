logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

resolvers += "Flyway" at "https://flywaydb.org/repo"

addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.14")

addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-RC13")

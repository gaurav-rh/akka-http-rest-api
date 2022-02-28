ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "RestApiAssignment"
  )

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.18"
// For Akka 2.4.x or 2.5.x
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.7"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.2.6"
// Only when running against Akka 2.5 explicitly depend on akka-streams in same version as akka-actor
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.18" // or whatever the latest version is
libraryDependencies += "com.typesafe.akka" %% "akka-actor"  % "2.6.18"
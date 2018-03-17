scalaVersion := "2.12.4"

addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.4" cross CrossVersion.binary)

scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-Ypartial-unification"
  )
  
libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.4"
    , "org.scalacheck" %% "scalacheck" % "1.13.5"
    , "org.typelevel" %% "cats-core" % "1.0.0"
    , "org.typelevel" %% "kittens" % "1.0.0-RC2"
    , "io.chymyst" %% "curryhoward" % "0.3.4"
  )

name := "scala-practice"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "2.2.0",

  // Start with this one
  "org.tpolecat" %% "doobie-core" % "0.9.0",

  // And add any of these as needed
  "org.tpolecat" %% "doobie-h2" % "0.9.0", // H2 driver 1.4.200 + type mappings.
  "org.tpolecat" %% "doobie-hikari" % "0.9.0", // HikariCP transactor.
  "dev.zio" %% "zio-interop-cats" % "2.2.0.1",
  compilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
)
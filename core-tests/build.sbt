name := "overflowdb-core-tests"

publish / skip := true

libraryDependencies ++= Seq(
  "com.github.sbt" % "junit-interface" % "0.13.3" % Test
)

Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")


githubOwner      := "Privado-Inc"
githubRepository := "overflowdb"

credentials +=
  Credentials(
    "GitHub Package Registry",
    "maven.pkg.github.com",
    "Privado-Inc",
    sys.env.getOrElse("GITHUB_TOKEN", "N/A")
  )
name := "overflowdb-traversal-tests"

publish / skip := true

Test / console / scalacOptions -= "-Xlint"


githubOwner      := "Privado-Inc"
githubRepository := "overflowdb"

credentials +=
  Credentials(
    "GitHub Package Registry",
    "maven.pkg.github.com",
    "Privado-Inc",
    sys.env.getOrElse("GITHUB_TOKEN", "N/A")
  )
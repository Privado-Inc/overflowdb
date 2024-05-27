name := "overflowdb-traversal"

libraryDependencies ++= Seq(
  "net.oneandone.reflections8" % "reflections8" % "0.11.7",
  "de.vandermeer" % "asciitable" % "0.3.2",
)

githubOwner      := "Privado-Inc"
githubRepository := "overflowdb"

credentials +=
  Credentials(
    "GitHub Package Registry",
    "maven.pkg.github.com",
    "Privado-Inc",
    sys.env.getOrElse("GITHUB_TOKEN", "N/A")
  )
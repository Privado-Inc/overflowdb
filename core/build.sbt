name := "overflowdb-core"

libraryDependencies ++= Seq(
  "net.sf.trove4j" % "core" % "3.1.0",
  "org.msgpack" % "msgpack-core" % "0.9.1",
  "com.h2database" % "h2-mvstore" % "1.4.200",
  "org.slf4j" % "slf4j-api" % "2.0.7"
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
name := "overflowdb-traversal"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "org.slf4j" % "slf4j-simple" % "1.7.28" % Test,
)

Test/console/scalacOptions -= "-Xlint"
Test/console/initialCommands :=
  """|import io.shiftleft.overflowdb.traversal._
     |import io.shiftleft.overflowdb.traversal.testdomains.gratefuldead._
     |val gd = GratefulDead.traversal(GratefulDead.newGraphWithData)""".stripMargin
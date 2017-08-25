name := """play-sandbox"""

lazy val root = (project in file(".")).enablePlugins(PlayJava)
  .dependsOn(admin)
  .aggregate(admin)
lazy val admin = (project in file("modules/admin")).enablePlugins(PlayJava)

Settings.root

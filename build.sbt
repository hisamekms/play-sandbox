name := """play-sandbox"""

lazy val root = (project in file(".")).enablePlugins(PlayJava)
  .settings(
    Settings.root
  )
  .dependsOn(admin, googleApi)
  .aggregate(admin, googleApi)

lazy val admin = (project in file("modules/admin"))
  .enablePlugins(PlayJava)
  .settings(
    Settings.admin
  )
lazy val googleApi = (project in file("modules/google-api")).enablePlugins(PlayJava)
  .settings(
    Settings.googleApi
  )



import sbt._
import Keys._
import play.sbt.PlayImport._

object Settings {
  val common: Seq[Setting[_]] = Seq(
    organization := "xyz.hisamekms",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.2"
  )

  val root = common ++ Seq(
    resolvers ++= Resolver.root,
    libraryDependencies ++= Dependency.root,
    javaOptions ++= Seq("-Dconfig.file=conf/root-dev.conf"),
    testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
  )

  val admin = common ++ Seq(
    resolvers ++= Resolver.admin,
    libraryDependencies ++= Dependency.admin,
    javaOptions in Test ++= Seq("-Dconfig.file=conf/admin-dev.conf"),
    testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
  )

  val googleApi = common ++ Seq(
    resolvers ++= Resolver.admin,
    libraryDependencies ++= Dependency.admin,
    javaOptions in Test ++= Seq("-Dconfig.file=conf/google-api-dev.conf"),
    testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
  )

}

object Dependency {
  val common = Seq(
    guice,
    "com.h2database" % "h2" % "1.4.196",
    "com.google.oauth-client" % "google-oauth-client" % "1.22.0",
    "com.google.oauth-client" % "google-oauth-client-java6" % "1.22.0",
    "com.google.http-client" % "google-http-client-jackson2" % "1.22.0",
    "com.google.api-client" % "google-api-client" % "1.22.0",
    "com.google.api-client" % "google-api-client-java6" % "1.22.0",
    "com.google.apis" % "google-api-services-drive" % "v3-rev81-1.22.0",
    "org.assertj" % "assertj-core" % "3.8.0" % Test,
    "org.awaitility" % "awaitility" % "3.0.0" % Test
  )
  val root = common ++ Seq()
  val admin = common ++ Seq()
  val googleApi = common ++ Seq()
}

object Resolver {
  val common = Seq()
  val root = common ++ Seq()
  val admin = common ++ Seq()
  val googleApi = common ++ Seq()
}

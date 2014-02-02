import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "MyApp"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "org.specs2" %% "specs2-core"  % "2.4-SNAPSHOT",
    "org.specs2" %% "specs2-gwt"   % "2.4-SNAPSHOT",
    "org.specs2" %% "specs2-junit" % "2.4-SNAPSHOT"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    resolvers ++= Seq(Resolver.sonatypeRepo("snapshots"))       
  )

}

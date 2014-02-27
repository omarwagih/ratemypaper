name := "rmp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "be.objectify"  %%  "deadbolt-java"     % "2.2.1-RC2",
  "commons-lang" % "commons-lang" % "2.6",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.apache.httpcomponents" % "httpclient" % "4.2.5",
  javaJdbc,
  javaEbean,
  cache
)     

play.Project.playJavaSettings

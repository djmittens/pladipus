lazy val platypus = (project in file(".")).settings(
  name := "platypus",
  description := "my algorithms practice",
  scalaVersion := "2.12.3",
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.5.4",
    "com.typesafe.akka" %% "akka-testkit" % "2.5.4" % Test
  ),
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-stream" % "2.5.4",
    "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.4" % Test
  ),
  libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",

  libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  libraryDependencies += "junit" % "junit" % "4.12" % "test",
  libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
  libraryDependencies += "org.pegdown"    %  "pegdown"     % "1.6.0"  % "test",

  testOptions in Test ++= Seq(Tests.Argument(TestFrameworks.ScalaTest, "-o"), Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports"))

)

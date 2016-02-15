name := "akka-scala-spring-mybatis"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "SpringSource Milestone Repository" at "http://repo.springsource.org/milestone"
resolvers += "OSChina Maven Repository" at "http://maven.oschina.net/content/groups/public/"

libraryDependencies ++= Seq(
  "com.typesafe.akka"         %%  "akka-actor"                % "2.3.2",
  "org.springframework.scala" %%  "spring-scala"              % "1.0.0.RC1",
  "org.springframework"       %   "spring-context"            % "3.2.8.RELEASE",
  "org.springframework"       %   "spring-jdbc"               % "3.2.8.RELEASE",
  "org.springframework"       %   "spring-test"               % "3.2.8.RELEASE" % "test",
  "com.h2database"            %   "h2"                        % "[1.3,)" % "test",
  "org.hsqldb"                %   "hsqldb"                    % "2.3.2",
  "mysql"                     %   "mysql-connector-java"      % "5.1.38",
  "commons-dbcp"              %   "commons-dbcp"              % "1.4",
  "ch.qos.logback"            %   "logback-classic"           % "[1.1,)",
  "com.github.nscala-time"    %%  "nscala-time"               % "0.8.0",
  "org.mybatis.scala"         %%  "mybatis-scala-core"        % "1.0.2",
  "org.mybatis"               %   "mybatis-spring"            % "1.2.2",
  "org.scalatest"             %%  "scalatest"                 % "2.1.3"           % "test"
)

net.virtualvoid.sbt.graph.Plugin.graphSettings

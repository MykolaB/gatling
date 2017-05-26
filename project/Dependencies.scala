import sbt._

object Dependencies { 

  /**************************/
  /** Compile dependencies **/
  /**************************/

  private def scalaReflect(version: String) = "org.scala-lang"                         % "scala-reflect"                 % version
  private val scalaSwing                    = "org.scala-lang.modules"                %% "scala-swing"                   % "2.0.0"
  private val scalaXml                      = "org.scala-lang.modules"                %% "scala-xml"                     % "1.0.6"
  private val scalaParserCombinators        = "org.scala-lang.modules"                %% "scala-parser-combinators"      % "1.0.6"
  private val java8Compat                   = "org.scala-lang.modules"                %% "scala-java8-compat"            % "0.8.0"
  private val ahc                           = "org.asynchttpclient"                    % "async-http-client"             % "2.1.0-alpha18"
  private val ahcNettyUtils                 = "org.asynchttpclient"                    % "async-http-client-netty-utils" % ahc.revision
  private val netty                         = "io.netty"                               % "netty-codec-http"              % "4.1.11.Final"
  private val nettyNativeTransport          = "io.netty"                               % "netty-transport-native-epoll"  % netty.revision classifier "linux-x86_64"
  private val akkaActor                     = "com.typesafe.akka"                     %% "akka-actor"                    % "2.5.1"
  private val akkaSlf4j                     = "com.typesafe.akka"                     %% "akka-slf4j"                    % akkaActor.revision
  private val config                        = "com.typesafe"                           % "config"                        % "1.3.1"
  private val saxon                         = "net.sf.saxon"                           % "Saxon-HE"                      % "9.7.0-18"
  private val slf4jApi                      = "org.slf4j"                              % "slf4j-api"                     % "1.7.25"
  private val fastring                      = "com.dongxiguo"                         %% "fastring"                      % "0.3.1"
  private val scopt                         = "com.github.scopt"                      %% "scopt"                         % "3.5.0"
  private val scalaLogging                  = "com.typesafe.scala-logging"            %% "scala-logging"                 % "3.5.0"
  private val jackson                       = "com.fasterxml.jackson.core"             % "jackson-databind"              % "2.8.8.1"
  private val jacksonCsv                    = "com.fasterxml.jackson.dataformat"       % "jackson-dataformat-csv"        % "2.8.8"
  private val boon                          = "io.advantageous.boon"                   % "boon-json"                     % "0.6.6" exclude("org.slf4j", "slf4j-api")
  private val jsonpath                      = "io.gatling"                            %% "jsonpath"                      % "0.6.9"
  private val joddLagarto                   = "org.jodd"                               % "jodd-lagarto"                  % "3.8.5"
  private val boopickle                     = "io.suzaku"                             %% "boopickle"                     % "1.2.6"
  private val redisClient                   = "net.debasishg"                         %% "redisclient"                   % "3.4"
  private val zinc                          = "com.typesafe.zinc"                      % "zinc"                          % "0.3.15" exclude("org.scala-lang", "scala-compiler")
  private val jmsApi                        = "org.apache.geronimo.specs"              % "geronimo-jms_1.1_spec"         % "1.1.1"
  private val logback                       = "ch.qos.logback"                         % "logback-classic"               % "1.2.3"
  private val tdigest                       = "com.tdunning"                           % "t-digest"                      % "3.1"
  private val hdrHistogram                  = "org.hdrhistogram"                       % "HdrHistogram"                  % "2.1.9"
  private val caffeine                      = "com.github.ben-manes.caffeine"          % "caffeine"                      % "2.5.0"
  private val bouncycastle                  = "org.bouncycastle"                       % "bcpkix-jdk15on"                % "1.56"
  private val quicklens                     = "com.softwaremill.quicklens"            %% "quicklens"                     % "1.4.8"
  private val testInterface                 = "org.scala-sbt"                          % "test-interface"                % "1.0"
  private val pebble                        = "com.mitchellbosecke"                    % "pebble"                        % "2.3.0"
  private val findbugs                      = "com.google.code.findbugs"               % "jsr305"                        % "3.0.1"

  /***********************/
  /** Test dependencies **/
  /***********************/

  private val scalaTest                      = "org.scalatest"                         %% "scalatest"                    % "3.0.3"             % "test"
  private val scalaCheck                     = "org.scalacheck"                        %% "scalacheck"                   % "1.13.5"            % "test"
  private val akkaTestKit                    = "com.typesafe.akka"                     %% "akka-testkit"                 % akkaActor.revision  % "test"
  private val mockitoCore                    = "org.mockito"                            % "mockito-core"                 % "2.3.11"            % "test"
  private val activemqCore                   = "org.apache.activemq"                    % "activemq-broker"              % "5.14.5"            % "test"
  private val h2                             = "com.h2database"                         % "h2"                           % "1.4.194"           % "test"
  private val ffmq                           = "net.timewalker.ffmq"                    % "ffmq3-core"                   % "3.0.7"             % "test" exclude("log4j", "log4j") exclude("javax.jms", "jms")
  private val jmh                            = "org.openjdk.jmh"                        % "jmh-core"                     % "1.19"
  private val jmh                            = "org.openjdk.jmh"                        % "jmh-core"                     % "1.19"
  private val apacheCommons                  = "org.apache.commons"                     % "commons-lang3"                % "3.5"


  private val loggingDeps = Seq(slf4jApi, scalaLogging, logback)
  private val testDeps = Seq(scalaTest, scalaCheck, akkaTestKit, mockitoCore)
  private val parserDeps = Seq(jsonpath, jackson, boon, saxon, joddLagarto)

  /****************************/
  /** Dependencies by module **/
  /****************************/

  def commonsDependencies(scalaVersion: String) =
    Seq(scalaReflect(scalaVersion), config, fastring, boopickle, quicklens, java8Compat, ahcNettyUtils, pebble) ++ loggingDeps ++ testDeps

  val coreDependencies =
    Seq(akkaActor, akkaSlf4j, jacksonCsv, boopickle, java8Compat, caffeine, scalaParserCombinators, scopt, findbugs, apacheCommons) ++
      parserDeps ++ testDeps

  val redisDependencies = redisClient +: testDeps

  val httpAhcDependencies = Seq(ahc, nettyNativeTransport) ++ testDeps

  val httpDependencies = Seq(scalaXml) ++ testDeps

  val jmsDependencies = Seq(jmsApi, activemqCore) ++ testDeps

  val jdbcDependencies = h2 +: testDeps

  val chartsDependencies = tdigest +: testDeps

  val metricsDependencies = hdrHistogram +: testDeps

  val benchmarkDependencies = Seq(jmh)

  def compilerDependencies(scalaVersion: String) =
    Seq(scalaReflect(scalaVersion), config, slf4jApi, logback, zinc, scopt)

  val recorderDependencies = Seq(scalaSwing, jackson, bouncycastle, netty, akkaActor) ++ testDeps

  val testFrameworkDependencies = Seq(testInterface)

  val docDependencies = Seq(ffmq)
}

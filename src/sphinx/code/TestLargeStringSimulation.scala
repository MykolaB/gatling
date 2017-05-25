package gatling_test

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:8002")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("uk,ru;q=0.8,en-US;q=0.5,en;q=0.3")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0")

  val headers_0 = Map("content-length" -> "0")

  val uri1 = "http://localhost:8002/greeting"

  val scn = scenario("RecordedSimulation")
    .exec(http("request_0")
      .get("/greeting")
      .headers(headers_0))
    .pause(5)
    .exec(http("request_1")
      .post("/greeting")
      .formParam("id", "0")
      .formParam("content", "1111"))
    .pause(1)
    .exec(http("request_2")
      .get("/greeting")
      .headers(headers_0))
    .pause(5)
    .exec(http("request_3")
      .post("/greeting")
      .formParam("id", "0")
      .formParam("content", joinStrings("1111111111", "1111111111", "1111111111", "1111111111", "1111111111")))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
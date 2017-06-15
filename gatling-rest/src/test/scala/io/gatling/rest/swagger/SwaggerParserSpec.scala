package io.gatling.rest.swagger

import io.gatling.BaseSpec

import scala.io.Source._

/**
  * Created by Mykola on 15.06.2017.
  */
class SwaggerParserSpec extends BaseSpec {

  val parser = new SwaggerParser()

  val spectText = fromInputStream(getClass.getResourceAsStream("SwaggerExample.json")).mkString

  "SwaggerParser" should "extract title" {
    val spec = parser.parse(spectText)
    spec.title shouldBe "Swagger Sample App"
  }

}

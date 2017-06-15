package io.gatling.rest.swagger

import io.gatling.{BaseSpec, ValidationValues}

import scala.io.Source._

/**
  * Created by Mykola on 15.06.2017.
  */
class SwaggerParserSpec extends BaseSpec with ValidationValues {

  val parser = new SwaggerParser()

  val spectText = fromInputStream(getClass.getClassLoader.getResourceAsStream("SwaggerExample.json")).mkString

  "SwaggerParser" should "extract attributes" in {
    val spec = parser.parse(spectText)
    spec.title shouldBe "Gatling sample swagger spec"
    spec.description shouldBe "This a sample swagger spec for testing"
  }

}

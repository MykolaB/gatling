package io.gatling.rest.swagger

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule


/**
  * Created by Mykola on 14.06.2017.
  */
class SwaggerParser {

  def parse(specificationJson: String): SwaggerSpecification = {
    val mapper = new ObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    mapper.readValue[SwaggerSpecification](specificationJson)
  }

}

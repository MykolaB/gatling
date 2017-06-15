package io.gatling.rest.swagger

import io.advantageous.boon.json.implementation.ObjectMapperImpl

/**
  * Created by Mykola on 14.06.2017.
  */
class SwaggerParser {

  def parse(specificationJson: String): SwaggerSpecification = {
    val mapper = new ObjectMapperImpl()
    mapper.fromJson[SwaggerSpecification](specificationJson, classOf[SwaggerSpecification])
  }

}

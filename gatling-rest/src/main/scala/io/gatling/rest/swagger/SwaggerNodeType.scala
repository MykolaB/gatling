package io.gatling.rest.swagger

/**
  * Created by mykbiletskyi on 09.06.2017.
  */
sealed trait SwaggerNodeType {

}


object SwaggerNodeType {
  case object Description extends SwaggerNodeType

  case object Title extends SwaggerNodeType
}
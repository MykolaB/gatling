package io.gatling.rest.swagger

/**
  * Created by mykbiletskyi on 09.06.2017.
  */
trait SwaggerNode {
  def nodeType: SwaggerNodeType
}

case class Text( textNodeType: SwaggerNodeType, text: String) extends SwaggerNode {
  override def nodeType = textNodeType
}
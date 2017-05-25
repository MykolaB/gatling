package io.gatling.core.util

import org.apache.commons.lang3.StringUtils


/**
  * Created by mykbiletskyi on 25.05.2017.
  */
object GatlingStringUtils {
  def join(strings: Seq[String]): String =
    StringUtils.join(strings)
}

/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gatling.core.feeder

import java.io.{InputStream, PipedInputStream, PipedOutputStream}
import java.util.{Map => JMap}

import com.fasterxml.jackson.databind.{MapperFeature, ObjectReader}
import com.fasterxml.jackson.dataformat.csv.{CsvMapper, CsvSchema}
import io.gatling.core.config.GatlingConfiguration
import io.gatling.core.util.Resource

import scalaz.stream._
import scala.collection.JavaConverters._

object SeparatedValuesParserZ {

  val CommaSeparator = ','
  val SemicolonSeparator = ';'
  val TabulationSeparator = '\t'

  def parse(resource: Resource, columnSeparator: Char, quoteChar: Char, escapeChar: Char)(implicit configuration: GatlingConfiguration): IndexedSeq[Record[String]] =
  {
    val pos = new PipedOutputStream()
    val pis = new PipedInputStream(pos)
    val channel = io.chunkR(resource.inputStream)
    val sink = io.chunkW(pos)

    val pipe = Process.constant(10000).through(channel).to(sink)
      //.to(io.chunkW(pos))

    stream(pis, columnSeparator, quoteChar, escapeChar).toVector
  }


  def stream(is: InputStream, columnSeparator: Char, quoteChar: Char, escapeChar: Char): Iterator[Record[String]] = {

    val mapper = new CsvMapper().disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
    val schema = CsvSchema.emptySchema.withHeader.withColumnSeparator(columnSeparator).withQuoteChar(quoteChar).withEscapeChar(escapeChar)

    val reader: ObjectReader = mapper.readerFor(classOf[JMap[_, _]])

    val it: Iterator[JMap[String, String]] = reader
      .`with`(schema)
      .readValues(is)
      .asScala

    it.map(_.asScala.toMap)
  }
}

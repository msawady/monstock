package services.domain

import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.bson.codecs.configuration.CodecRegistries.{fromRegistries, fromProviders}


case class Stock(_id: ObjectId, code: String, name: String, market: String, industryType: String, unit: Int, isNK225: Boolean)

object Stock {
  def apply(code: String, name: String, market: String, industryType: String, unit: Int, isNK225: Boolean): Stock
  = new Stock(new ObjectId, code, name, market, industryType, unit, isNK225)

  val codecRegistry = fromRegistries(fromProviders(classOf[Stock]), DEFAULT_CODEC_REGISTRY)
}


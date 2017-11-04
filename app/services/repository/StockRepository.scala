package services.repository

import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Singleton

import org.mongodb.scala.{MongoClient, MongoCollection}
import play.Environment
import services.domain.Stock
import services.repository.Helpers._

import scala.io.Source

/**
  * Created by masaa on 2017/10/09.
  */
@Singleton
class StockRepository {

  val mongoClient = MongoClient()
  val database = mongoClient.getDatabase("monstock").withCodecRegistry(Stock.codecRegistry)
  val initialized = new AtomicBoolean()


  def toStock(data: String): Stock = {
    val el = data.split(",")
    Stock(el(0), el(1), el(2), el(3), safeStringToInt(el(4)).getOrElse(0), if (el.length > 5) "1".equals(el(5)) else false)
  }

  def initializeIfNeeded(): Unit = {
    if (initialized.get()) {
      return
    }

    val collection: MongoCollection[Stock] = database.getCollection("stock")
    collection.drop().results()

    val s = Source.fromFile(Environment.simple().getFile("data/stocklist.csv"), "UTF-8")
    val lines = s.getLines().toList
    val stocks: List[Stock] = lines.tail.map(l => toStock(l)).toList

    collection.insertMany(stocks).results()
    this.initialized.set(true)
  }

  def getStockList(): List[Stock] = {
    initializeIfNeeded()

    val collection: MongoCollection[Stock] = database.getCollection("stock")
    return collection.find().results().toList
  }

  def safeStringToInt(str: String): Option[Int] = {
    import scala.util.control.Exception._
    catching(classOf[NumberFormatException]) opt str.toInt
  }

}

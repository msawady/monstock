package controllers

import javax.inject._

import play.api.mvc._
import services.StockManager

/**
  * controllers of Stock
  */
@Singleton
class StockController @Inject()(cc: ControllerComponents, stockManager: StockManager, stockView:views.html.stock) extends AbstractController(cc) {

  def list = Action {
    Ok(stockView(stockManager.list()))
  }

}

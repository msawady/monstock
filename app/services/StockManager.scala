package services

import javax.inject.Inject

import com.google.inject.Singleton
import services.repository.StockRepository

/**
  * service class for todo management
  */
@Singleton
class StockManager @Inject()(stockRepository: StockRepository) {


  /**
    * get Todo List
    *
    * @return todo-list order by id
    */
  def list() = stockRepository.getStockList()


}

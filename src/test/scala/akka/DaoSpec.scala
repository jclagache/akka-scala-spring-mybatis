package akka

import org.scalatest._
import akka.matchers.CargoDateMatchers
import java.util.Date
import akka.config.{MyBatisTestConfig}
import akka.dao.mybatis.mapper.{GoodDao, ContainerDao, CargoDao}
import org.springframework.test.context.{TestContextManager, ContextConfiguration}
import org.springframework.scala.test.context.{FunctionalConfigurations, FunctionalConfigContextLoader}
import org.springframework.beans.factory.annotation.Autowired
import scala.collection.JavaConverters._

@ContextConfiguration(loader = classOf[FunctionalConfigContextLoader])
@FunctionalConfigurations(classOf[MyBatisTestConfig])
class DaoSpec extends FlatSpec with Matchers with BeforeAndAfter with CargoDateMatchers {

  new TestContextManager(this.getClass()).prepareTestInstance(this)

  @Autowired
  var cargoDao : CargoDao = _
  @Autowired
  var containerDao : ContainerDao = _
  @Autowired
  var goodDao : GoodDao = _


//===================================================== CargoDao =======================================================

  "CargoDao" should "return the cargo 'cargo 2' with arrival=20 and departure=25" in {
    val cargo = cargoDao.findByRef("cargo 2")
    cargo should not be null
    cargo should have (
      'ref ("cargo 2"),
      arrival (20),
      departure (25)
    )
  }

    it should "not return any result when looking for 'cargo 11'" in {
      val cargo = cargoDao.findByRef("cargo 11")
      cargo should be (null)
    }


  it should "return the cargo 5 to 9 when looking for the cargoes arrived between 50 and 100" in {
    val cargoes = cargoDao.findAllBetween(new Date(50), new Date(100)).asScala
    cargoes should have size 5
    val cargoRefs = cargoes.map(_.ref)
    cargoRefs should contain only ("cargo 5", "cargo 6", "cargo 7", "cargo 8", "cargo 9")
    cargoes.forall( cargo =>
      cargo.arrival.getMillis >= 50 &&
      cargo.departure.getMillis < 100
    ) should be (true)
  }

  it should "return the cargo 6 to 10 when looking for the cargoes arrived between 51 and 101" in {
    val cargoes = cargoDao.findAllBetween(new Date(51), new Date(101)).asScala
    cargoes should have size 5
    val cargoRefs = cargoes.map(_.ref)
    cargoRefs should contain only ("cargo 6", "cargo 7", "cargo 8", "cargo 9", "cargo 10")
    cargoes.forall( cargo =>
      cargo.arrival.getMillis >= 51 &&
      cargo.arrival.getMillis < 101
    ) should be (true)
  }

  it should "return the cargo 5 to 10 when looking for the cargoes arrived after 50" in {
    val cargoes = cargoDao.findAllSince(new Date(50)).asScala
    cargoes should have size 6
    val cargoRefs = cargoes.map(_.ref)
    cargoRefs should contain only ("cargo 5", "cargo 6", "cargo 7", "cargo 8", "cargo 9", "cargo 10")
    cargoes.forall(_.arrival.getMillis >= 50) should be (true)
  }

  it should "return the cargo 6 to 10 when looking for the cargoes arrived after 51" in {
    val cargoes = cargoDao.findAllSince(new Date(51)).asScala
    cargoes should have size 5
    val cargoRefs = cargoes.map(_.ref)
    cargoRefs should contain only ("cargo 6", "cargo 7", "cargo 8", "cargo 9", "cargo 10")
    cargoes.forall(_.arrival.getMillis >= 51) should be (true)
  }



//=================================================== ContainerDao =====================================================

  "ContainerDao" should "return the container 'C039' with cargoRef='cargo 10'" in {
    val container =  containerDao.findByRef("C039")
    container should not be null
    container should have (
      'ref ("C039"),
      'cargoRef ("cargo 10")
    )
  }

  it should "not return any result when looking for 'C040'" in {
    val container = containerDao.findByRef("C040")
    container should be (null)
  }

  it should "return the containers 3 to 6 when looking for the containers in the cargo 'cargo 2'" in {
    val containers =  containerDao.findAllInCargo("cargo 2").asScala
    containers should have size 4
    val containerRefs = containers.map(_.ref)
    containerRefs should contain only ("C003", "C004", "C005", "C006")
    containers.forall(_.cargoRef == "cargo 2") should be (true)
  }



//===================================================== GoodDao ========================================================

  "GoodDao" should "return the container 'G099' with type='T00', containerRef='C039', quantity=1" in {
    val good =  goodDao.findByRef("G099")
    good should not be null
    good should have (
      'ref ("G099"),
      'goodType ("T00"),
      'containerRef ("C039"),
      'quantity (1)
    )
  }

  it should "not return any result when looking for 'G100'" in {
    val goodOpt = goodDao.findByRef("G100")
    goodOpt should be (null)
  }

  it should "return the goods 3 to 5 when looking for the containers in the container 'C002'" in {
    val goods = goodDao.findAllInContainer("C002").asScala
    val containerRefs = goods.map(_.ref)
    containerRefs should contain only ("G003", "G004", "G005")
    goods.forall( good =>
      good.containerRef == "C002" &&
      good.goodType == "T00" &&
      good.quantity == 1
    ) should be (true)
  }

}

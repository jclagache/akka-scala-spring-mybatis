package akka.dao.mybatis.mapper

import org.apache.ibatis.annotations.Param
import akka.data.Container

trait ContainerDao {

  def findByRef(@Param("ref")ref: String): Container

  def findAllInCargo(@Param("cargoRef")ref: String): java.util.List[Container]

}

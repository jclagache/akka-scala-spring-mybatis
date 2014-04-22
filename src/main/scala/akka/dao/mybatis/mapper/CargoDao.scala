package akka.dao.mybatis.mapper

import akka.data.Cargo
import org.apache.ibatis.annotations.Param
import java.util.Date

trait CargoDao {

  def findByRef(@Param("ref")ref: String): Cargo

  def findAllBetween(@Param("first")first: Date, @Param("last")last: Date): java.util.List[Cargo]

  def findAllSince(@Param("date")date: Date): java.util.List[Cargo]

}
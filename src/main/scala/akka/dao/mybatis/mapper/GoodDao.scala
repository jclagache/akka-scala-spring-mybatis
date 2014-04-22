package akka.dao.mybatis.mapper

import org.apache.ibatis.annotations.Param
import akka.data.Good

trait GoodDao {

   def findByRef(@Param("ref")ref: String): Good

   def findAllInContainer(@Param("containerRef")ref: String): java.util.List[Good]

 }

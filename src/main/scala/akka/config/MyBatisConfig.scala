package akka.config

import org.springframework.scala.context.function.{ContextSupport, FunctionalConfiguration}
import org.mybatis.spring.{SqlSessionTemplate, SqlSessionFactoryBean}
import javax.sql.DataSource
import org.mybatis.spring.mapper.MapperScannerConfigurer


/**
 * Created by j-c.lagache on 17/04/2014.
 */
abstract class MyBatisConfig extends FunctionalConfiguration with ContextSupport {

  def dataSource: DataSource

  bean("sqlSessionFactory") {
    new SqlSessionFactoryBean() {
      setDataSource(dataSource)
    }
  }

  bean("sqlSessionTemplate") {
    new SqlSessionTemplate(
      new SqlSessionFactoryBean() {
        setDataSource(dataSource)
      }.getObject
    )
   }

  bean() {
    new MapperScannerConfigurer {
      setBasePackage("akka.dao.mybatis.mapper")
      setSqlSessionTemplateBeanName("sqlSessionTemplate")
    }
  }

}

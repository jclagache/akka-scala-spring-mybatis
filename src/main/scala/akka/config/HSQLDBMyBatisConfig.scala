package akka.config

import javax.sql.DataSource
import akka.actor.Actor
import akka.actor.Actor.Receive
import akka.actor.worker.CargoWorker
import akka.dao.mybatis.mapper.CargoDao
import org.apache.commons.dbcp.BasicDataSource
import org.mybatis.spring.{SqlSessionFactoryBean, SqlSessionTemplate}

/**
  * Created by dan on 2/14/16.
  */
class HSQLDBMyBatisConfig extends MyBatisConfig {
   override def dataSource: DataSource = {
    val ds = new BasicDataSource
    ds.setDriverClassName("org.hsqldb.jdbc.JDBCDriver")
    ds.setUsername("SA")
    ds.setPassword("")
    ds.setMaxActive(20)
    ds.setMaxIdle(10)
    ds.setInitialSize(10)
    ds.setValidationQuery("SELECT 1 FROM INFORMATION_SCHEMA.SYSTEM_USERS")
    new java.io.File("target").mkdirs // ensure that folder for database exists
    ds.setUrl("jdbc:hsqldb:file:target/db/db")
    ds
  }
}

package akka.config

import javax.sql.DataSource

import org.apache.commons.dbcp.BasicDataSource

/**
  * Created by dan on 2/14/16.
  */
class MySQLMyBatisConfig extends MyBatisConfig {
   override def dataSource: DataSource = {
    val ds = new BasicDataSource
    ds.setDriverClassName("com.mysql.jdbc.Driver")
    ds.setUsername("root")
    ds.setPassword("123456")
    ds.setMaxActive(20)
    ds.setMaxIdle(10)
    ds.setInitialSize(10)
    ds.setValidationQuery("select 1 from dual")
    ds.setUrl("jdbc:mysql://localhost:3306/my_test1?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull")
    ds
  }
}

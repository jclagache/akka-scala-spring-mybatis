package akka.config

import org.springframework.jdbc.datasource.embedded.{EmbeddedDatabaseType, EmbeddedDatabaseBuilder}

/**
 * Created by j-c.lagache on 18/04/2014.
 */
class MyBatisTestConfig extends MyBatisConfig {

  val dataSource = {
    val databaseBuilder = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("schema.sql").addScript("data.sql")
    databaseBuilder.build
  }

}

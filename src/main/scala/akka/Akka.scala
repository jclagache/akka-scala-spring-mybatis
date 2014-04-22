package akka

import akka.actor.{Props, ActorSystem}

import akka.actor.Messages._
import akka.actor.MyTask
import org.springframework.scala.context.function.FunctionalConfigApplicationContext
import akka.config.{MyBatisConfig, ActorConfig}
import akka.config.extension.SpringExtentionImpl
import org.joda.time.Interval


object Akka extends App {

  def time[R](block: => R): R = {
    val t0 = System.currentTimeMillis()
    val result = block // call-by-name
    val t1 = System.currentTimeMillis()
    println("Elapsed time: " + (t1 - t0) + "ms")
    result
  }

  // create a spring context
  implicit val ctx = FunctionalConfigApplicationContext(classOf[MyBatisConfig],classOf[ActorConfig])

  // get hold of the actor system
  val system = ctx.getBean(classOf[ActorSystem])

  val task = new MyTask(new Interval(50, 100)) {
    // use the Spring Extension to create props for a named actor bean
    cargoWorkerRouter = system.actorOf(SpringExtentionImpl(system).props("cargoWorker"), "cargoWorkerRouter")
    containerWorkerRouter = system.actorOf(SpringExtentionImpl(system).props("containerWorker"), "containerWorkerRouter")
    goodWorkerRouter = system.actorOf(SpringExtentionImpl(system).props("goodWorker"), "goodWorkerRouter")
    xmlWorker = system.actorOf(SpringExtentionImpl(system).props("xmlWorker"), "xmlBuilder")
  }

  val taskActor = system.actorOf(Props(task), "master")

  time {
    taskActor ! StartBatch
    system.awaitTermination()
  }

}

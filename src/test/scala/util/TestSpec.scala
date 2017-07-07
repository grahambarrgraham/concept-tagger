package util

import akka.actor.{ ActorRef, ActorSystem, PoisonPill }
import akka.testkit.TestProbe
import akka.util.Timeout
import org.scalatest.{ BeforeAndAfterAll, FlatSpec, Matchers, OptionValues }
import org.scalatest.concurrent.{ Eventually, ScalaFutures }

import scala.concurrent.{ ExecutionContext, Future }
import scala.concurrent.duration._
import scala.util.{ Failure, Try }

abstract class TestSpec extends FlatSpec with Matchers with OptionValues with ScalaFutures with BeforeAndAfterAll with Eventually {
  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = system.dispatcher
  implicit val timeout: Timeout = 1.second
  implicit val pc: PatienceConfig = PatienceConfig(timeout = 60.minutes, interval = 300.millis)

  implicit class PimpedFuture[T](self: Future[T]) {
    def toTry: Try[T] = Try(self.futureValue)
    def throwable: Throwable = self.toTry match {
      case Failure(t) => t
      case _ => fail("Future did not fail")
    }
    def throwableMessage: String = throwable.getMessage
  }

  def killActors(actors: ActorRef*): Unit = {
    val tp = TestProbe()
    actors.foreach { (actor: ActorRef) =>
      tp watch actor
      actor ! PoisonPill
      tp.expectTerminated(actor)
    }
  }

  override protected def afterAll(): Unit = {
    system.terminate().toTry should be a 'success
  }
}
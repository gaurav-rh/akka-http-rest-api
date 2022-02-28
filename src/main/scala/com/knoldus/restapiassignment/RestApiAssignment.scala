package com.knoldus.restapiassignment

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.knoldus.restapiassignment.Protocols._
import spray.json.{JsArray, JsValue, JsonFormat, RootJsonFormat, enrichAny}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.io.StdIn

import com.knoldus.restapiassignment.UserImpl

object RestApiAssignment extends App {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val userImpl = new UserImpl()

  implicit def listBufferImplicit[T: JsonFormat] = new RootJsonFormat[ListBuffer[T]] {
    override def write(obj: ListBuffer[T]): JsValue = JsArray(obj.map(_.toJson).toVector)

    override def read(json: JsValue): ListBuffer[T] = ???
  }

  val route = {
    (path("getAllUser") & get) {
      complete(userImpl.getAllUsers().toJson.prettyPrint)
    } ~
      (path("getUser" / Segment) & get) { name =>
        complete(userImpl.getUser(name).toJson.prettyPrint)
      } ~
      (path("getUser" / IntNumber) & get) { id =>
        complete(userImpl.getUser(id).toJson.prettyPrint)
      } ~
      (path("addUser") & post) {
        parameters("name", "age".as[Int], "email") { (name, age, email) =>
          userImpl.addUser(name,age,email)
          complete("new user added")
        }
      } ~
      (path("deleteUser" / IntNumber) & delete) { id =>
        userImpl.deleteUser(id)
        complete(s"deleted user with id number $id")
      } ~
      pathEndOrSingleSlash {
        get {
          complete(userImpl.getAllUsers().toJson.prettyPrint)
        }
      }
  }



val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandleAsync (route, "localhost", 8010)
  println (s"Server online at http://localhost:8010/\nPress RETURN to stop...")
  StdIn.readLine ()
  bindingFuture
  .flatMap (_.unbind() )
  .onComplete (_ => system.terminate())


  }

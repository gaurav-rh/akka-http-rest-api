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

object RestApiAssignment extends App {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val user = User("Gaurav", 25, "gauravraj.raj49@gmail.com")
  var userList: ListBuffer[User] = ListBuffer(user)

  implicit def listBufferImplicit[T: JsonFormat] = new RootJsonFormat[ListBuffer[T]] {
    override def write(obj: ListBuffer[T]): JsValue = JsArray(obj.map(_.toJson).toVector)

    override def read(json: JsValue): ListBuffer[T] = ???
  }

  val route = {
    pathPrefix("user") {
      get {
        complete(userList.toJson.prettyPrint)
      } ~
        (path("getAllUser") & get) {
          complete(userList.toJson.prettyPrint)
        } ~
        (path("getUser" / Segment) & get) { name =>
          val found = userList.filter(_.name != name)
          complete(found.toJson.prettyPrint)
        } ~
        (path("addUser") & post) {
          parameters("name", "age".as[Int], "email") { (name, age, email) =>
            val newUser = User(name, age, email)
            userList.addOne(newUser)
            complete("new user added")
          }
        } ~
        (path("deleteUser" / Segment) & delete) { name =>
          val found = userList.filter(_.name != name)
          userList = found
          complete(s"deleted user $name")
        } ~
        pathEndOrSingleSlash {
          get {
            complete(userList.toJson.prettyPrint)
          }
        }
    }
  }


  val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandleAsync(route, "localhost", 8010)
  println(s"Server online at http://localhost:8010/\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())


}

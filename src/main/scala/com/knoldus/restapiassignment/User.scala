package com.knoldus.restapiassignment

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import spray.json
final case class User(id:Int, name: String, age: Int , email: String)

object Protocols extends DefaultJsonProtocol {
  implicit val UserFormat: RootJsonFormat[User] = jsonFormat4(User)
}

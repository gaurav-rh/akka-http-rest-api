package com.knoldus.restapiassignment

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import spray.json
final case class User(name: String, age: Int , email: String)

object Protocols extends DefaultJsonProtocol {
  implicit val UserFormat: RootJsonFormat[User] = jsonFormat3(User)
}

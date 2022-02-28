package com.knoldus.restapiassignment

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

class UserImpl extends UserRepo {


  //val user: User = User(1,"Gaurav", 25, "gauravraj.raj49@gmail.com")

  var userList: ListBuffer[User] = ListBuffer()

  var Uid: Int = userList.length

  override  def getUser(name:String):ListBuffer[User] = userList.filter(_.name == name)

  override def getAllUsers(): ListBuffer[User] = userList

  override def addUser(name: String, age: Int, emailId: String): ListBuffer[User] = {

    Uid = Uid +1

    val newUser = User(Uid,name, age, emailId)
    userList.addOne(newUser)

  }

  override def deleteUser(id: Int) = {
    val found = userList.filter(_.id != id)
    userList = found
    userList
  }
}

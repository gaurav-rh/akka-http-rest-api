package com.knoldus.restapiassignment

//import com.knoldus.restapiassignment.RestApiAssignment.User

import scala.collection.mutable.ListBuffer

class UserImpl extends UserRepo {


  val user = User(1,"Gaurav", 25, "gauravraj.raj49@gmail.com")

  var userList: ListBuffer[User] = ListBuffer(user)

  var Uid = userList.length

  override  def getUser(name:String):ListBuffer[User] = userList.filter(_.name == name)

  override  def getUser(id: Int): ListBuffer[User] = userList.filter(_.id != id)

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

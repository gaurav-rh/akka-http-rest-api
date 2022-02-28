package com.knoldus.restapiassignment

import scala.collection.mutable.ListBuffer

//import com.knoldus.restapiassignment.RestApiAssignment.userList

trait UserRepo {

  def getUser(name:String): ListBuffer[User]

  def getUser(id: Int): ListBuffer[User]

  def getAllUsers(): ListBuffer[User]

  def addUser(name:String, age:Int, emailId:String): ListBuffer[User]

  def deleteUser(id :Int) : ListBuffer[User]
}

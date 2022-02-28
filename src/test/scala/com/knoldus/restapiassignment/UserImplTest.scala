package com.knoldus.restapiassignment

import org.scalatest.flatspec.AnyFlatSpec

import scala.collection.mutable.ListBuffer


class UserImplTest extends AnyFlatSpec {


  behavior of "UserImplTest"
  val userRepo = new UserImpl
  val gaurav = User(1,"Gaurav", 25, "gauravraj.raj49@gmail.com")
  val bhavya = User(id=2,name = "bhavya", age = 25 ,email = "bhavya@knoldus.com")
  userRepo.addUser("Gaurav", 25, "gauravraj.raj49@gmail.com")
  userRepo.addUser(name = "rishabh", age = 26, emailId="reshu@cloudcover.com")

  it should "addUser" in {
    assertResult(ListBuffer(gaurav))(userRepo.getUser("Gaurav"))
  }


  it should "getUser" in {
    assertResult(ListBuffer(gaurav))(userRepo.getUser("Gaurav"))
  }

  it should "deleteUser" in {
    val rishabh = User(id=2,name = "rishabh", age = 26, email="reshu@cloudcover.com")
    assertResult(ListBuffer(gaurav))(userRepo.deleteUser(rishabh.id))
  }

}


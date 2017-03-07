package com.dgarg20.services

import com.dgarg20.StartApp
import com.dgarg20.models.{NewUser, UserLogin}
import com.dgarg20.repositories.UserRepositoryImpl

import scala.concurrent.Future
import slick.driver.MySQLDriver.api._
import com.dgarg20.encrypt_decrypt._
/**
  * Created by deepanshu.garg@yatraonline.local on 2/3/17.
  */
class UserServiceImpl extends UserService{
  var db=Database.forConfig("mysql")
  val userRepository = new UserRepositoryImpl
  val encryptDecrypt= new EncryptDecrypt
  implicit val ec = StartApp.executionContext

  override def authenticateUser(userLogin: UserLogin):Future[String] = {
    db.run(userRepository.authenticateUser(userLogin.uName)).map { res =>
      res match {
        case Some(s) =>
          if (userLogin.password == encryptDecrypt.decrypt("Donald Trump is an idiot", s.password))
            "User Authenticated"
          else throw new Exception("username password doesn't match")
        case None => throw new Exception("user doesn't exist")


      }
    }
  }

  override def createUser(user: NewUser): Future[Boolean] = {
    val encrpytedUser = user.copy(password = encryptDecrypt.encrypt("Donald Trump is an idiot" , user.password))
    db.run(userRepository.createUser(encrpytedUser)).map{x =>
      true
    }.recover{
      case ex: Exception => false
    }
  }

}

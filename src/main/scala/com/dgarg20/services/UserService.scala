package com.dgarg20.services

import com.dgarg20.models.{NewUser, UserLogin}

import scala.concurrent.Future

/**
  * Created by deepanshu.garg@yatraonline.local on 2/3/17.
  */
trait UserService {
  def createUser(user:NewUser):Future[Boolean]
  def authenticateUser(userLogin : UserLogin) : Future[String]

}

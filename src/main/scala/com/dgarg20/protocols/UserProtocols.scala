package com.dgarg20.protocols

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.dgarg20.models.{NewUser, UserLogin}
import spray.json.DefaultJsonProtocol

/**
  * Created by deepanshu.garg@yatraonline.local on 2/3/17.
  */
class UserProtocols extends DefaultJsonProtocol with SprayJsonSupport{
implicit  val userLoginJF=jsonFormat2(UserLogin.apply)
  implicit val newUser= jsonFormat5(NewUser.apply)
}

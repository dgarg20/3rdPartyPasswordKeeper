package com.dgarg20.controllers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, entity, get, onSuccess, parameters, path, put, _}
import com.dgarg20.models.{NewUser, UserLogin}
import com.dgarg20.protocols.UserProtocols
import com.dgarg20.services.UserServiceImpl
import spray.json.{JsObject, JsString}

/**
  * Created by deepanshu.garg@yatraonline.local on 2/3/17.
  */
class UserController extends UserProtocols{

  val userService = new UserServiceImpl

  def route = path("api" / "user") {
    get {
      parameters('uname, 'pass) { (user, pass) =>
        //val hup = keeperService.getHostPasswords(user, key)
        onSuccess(userService.authenticateUser(UserLogin(user , pass))) { res =>
          complete(res)
        }
      }
    }~
      post {
        entity(as[NewUser]){ js=>
           onSuccess(userService.createUser(js)){res =>
            complete(StatusCodes.OK)
          }
        }
      }
  }
}

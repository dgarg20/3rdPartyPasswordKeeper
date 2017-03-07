package com.dgarg20.controllers

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import com.dgarg20.models.{DeleteHostPassword, KeeperWithKey}
import com.dgarg20.protocols.KeeperProtocol
import com.dgarg20.services. KeeperServicesImpl
import spray.json.{JsObject, JsString}

/**
  * Created by deepanshu.garg@yatraonline.local on 27/2/17.
  */
class KeeperController extends KeeperProtocol {
  val keeperService = new KeeperServicesImpl

  def route = path("api" / "Keeper") {
    get {
      parameters('host, 'hostname, 'key) {
        (user, hostname , key) =>

        onSuccess(keeperService.getHostPasswords(user, hostname, key)) { res =>
          complete(res)
        }
      }
    } ~
    post{
      entity(as[KeeperWithKey]) { hup =>
        onSuccess(keeperService.createHostPassword(hup)) { created =>
          complete(StatusCodes.Created)

        }

      }
    }~
    put {
      entity(as[KeeperWithKey]) { keeper =>
        onSuccess(keeperService.updateHostPassword(keeper)) { res =>
          complete(StatusCodes.Created)

        }
      }
    }~
    delete {
      entity(as[DeleteHostPassword]){ deleteHostPassword =>
        onSuccess(keeperService.deleteHostPassword(deleteHostPassword)){res =>
          complete(StatusCodes.OK)
        }

      }
    }
  } ~
    path("api" / "Keeper" / "allHosts") {
      get {
        parameters('uname) { uName =>
          onSuccess(keeperService.getAllHosts(uName)) { res =>
            complete(res)

          }
        }
      }
    }
}

package com.dgarg20.protocols

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.dgarg20.models.{DeleteHostPassword, HUP, Keeper, KeeperWithKey}
import spray.json.DefaultJsonProtocol

/**
  * Created by deepanshu.garg@yatraonline.local on 28/2/17.
  */
class KeeperProtocol extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val keeper= jsonFormat4(Keeper.apply)
  implicit val hupJF = jsonFormat3(HUP.apply)
  implicit val keeperWithKeyJF=jsonFormat5(KeeperWithKey.apply)
  implicit val deleteHostJF=jsonFormat2(DeleteHostPassword.apply)
}

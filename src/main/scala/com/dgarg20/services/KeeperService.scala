package com.dgarg20.services

import com.dgarg20.models._
import slick.dbio.DBIO

import scala.concurrent.Future

/**
  * Created by deepanshu.garg@yatraonline.local on 28/2/17.
  */
trait KeeperService {
  def getAllHosts(uName:String):Future[Seq[String]]
  def getHostPasswords(uname : String , hostName:String , key : String):Future[HUP]
  def updateHostPassword(keeper:KeeperWithKey):Future[Int]
  def deleteHostPassword(deleteHostPassword: DeleteHostPassword) :Future[Int]
  def createHostPassword(keeper:KeeperWithKey):Future[Int]

}
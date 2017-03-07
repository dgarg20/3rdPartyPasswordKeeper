package com.dgarg20.repositories

import com.dgarg20.models._
import slick.dbio.DBIO

import scala.concurrent.Future

/**
  * Created by deepanshu.garg@yatraonline.local on 28/2/17.
  */
trait KeeperRepository {



  def getHostPasswords(uName : String , hostName: String):DBIO[Option[Keeper]]
  def updateHostPassword(keeper:Keeper) : DBIO[Int]
  def deleteHostPassword(deleteHostPassword: DeleteHostPassword) : DBIO[Int]
  def createHostPassword(keeper : Keeper) : DBIO[Int]
  def getAllHosts(uName: String) : DBIO[Seq[Keeper]]


}

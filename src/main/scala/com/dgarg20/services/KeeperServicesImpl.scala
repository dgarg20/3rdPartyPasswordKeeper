package com.dgarg20.services

import com.dgarg20.StartApp
import com.dgarg20.models._
import com.dgarg20.repositories.KeeperRepositoryImpl
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future
import com.dgarg20.encrypt_decrypt._
/**
  * Created by deepanshu.garg@yatraonline.local on 27/2/17.
  */
class KeeperServicesImpl extends KeeperService{

  val keeperRepository=new KeeperRepositoryImpl

  var db=Database.forConfig("mysql")
implicit val ec = StartApp.executionContext
  val encryptDecrypt=new EncryptDecrypt



  override def getHostPasswords(uName:String, hostName: String , key:String): Future[HUP]= {
    db.run(keeperRepository.getHostPasswords(uName, hostName)).map {
      res=> res match {
        case Some(s: Keeper) => HUP (s.hostName, s.hostUName, encryptDecrypt.decrypt (key, (s.hostPassword) ) )
        case None => throw new Exception("hostname does not exists")
      }

    }
  }

  override def updateHostPassword(keeper:KeeperWithKey):Future[Int] = {
    val updateKeeper = Keeper(keeper.uName , keeper.hostName , keeper.hostUName ,
      encryptDecrypt.encrypt(keeper.key,keeper.hostPassword))
    db.run(keeperRepository.updateHostPassword(updateKeeper))
  }



  override def deleteHostPassword(deleteHostPassword: DeleteHostPassword) : Future[Int]= {
    db.run(keeperRepository.deleteHostPassword(deleteHostPassword: DeleteHostPassword))
  }


  override def createHostPassword(keeper : KeeperWithKey) : Future[Int] = {
     val newKeeper = Keeper(keeper.uName , keeper.hostName , keeper.hostUName , encryptDecrypt.encrypt(keeper.key,keeper.hostPassword))
      db.run(keeperRepository.createHostPassword(newKeeper))
  }

  override def getAllHosts(uName: String) : Future[Seq[String]] ={
  db.run(keeperRepository.getAllHosts(uName)).map( xs => xs.map{ res => res.hostName})

  }
}





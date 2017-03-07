package com.dgarg20.repositories
import com.dgarg20.StartApp
import com.dgarg20.models._
import slick.dbio.DBIO
import slick.driver.MySQLDriver.api._

/**
  * Created by deepanshu.garg@yatraonline.local on 27/2/17.
  */


class KeeperTable(tag:Tag) extends Table[Keeper](tag, "keeper") {
  def uName=column[String]("uname")
  //def password=column[String]("password")
  def hostName=column[String]("hostname")
  def hostUName=column[String]("hostusername")
  def hostPassword=column[String]("hostpassword")
  def * = (uName , hostName , hostUName , hostPassword) <> (Keeper.tupled , Keeper.unapply)
}



class KeeperRepositoryImpl extends KeeperRepository {
  implicit val ec = StartApp.executionContext
  protected lazy val UserTable = TableQuery[UserTable]
  protected lazy val KeeperTable = TableQuery[KeeperTable]


  def getHostPasswords(uName : String , hostName: String) : DBIO[Option[Keeper]] = {
    KeeperTable.filter( x => x.uName === uName && x.hostName === hostName).result.headOption
  }

  override def updateHostPassword(keeper: Keeper ) : DBIO[Int] = {
    KeeperTable.filter(res => res.hostName === keeper.hostName && res.uName === keeper.uName).update(keeper).map{
      case 1 => 1
      case x => throw new Exception("doesn't exists")
    }
  }

  override def deleteHostPassword(deleteHostPassword: DeleteHostPassword) :DBIO[Int] = {
    KeeperTable.filter(x => x.uName === deleteHostPassword.uName && x.hostName === deleteHostPassword.hostName).delete.map{
      case 1 => 1
      case x => throw new Exception("does not exist")
    }
  }


  override def createHostPassword(keeper:Keeper):DBIO[Int] = {
    KeeperTable += keeper
  }

  override def getAllHosts(uName: String) :DBIO[Seq[Keeper]] ={
    val q = for {
      query <-  KeeperTable.filter(_.uName === uName)
    } yield  query

    q.result
  }
}

package com.dgarg20.repositories

import com.dgarg20.models.NewUser
import slick.dbio.DBIO
import slick.driver.MySQLDriver.api._
import com.dgarg20.StartApp

/**
  * Created by deepanshu.garg@yatraonline.local on 2/3/17.
  */
  class UserTable(tag:Tag) extends Table[NewUser](tag,"user")
  {
    def uID = column[Int]("uid",O.PrimaryKey , O.AutoInc)
    def uName=column[String]("uname")
    def password=column[String]("password")
    def mobile=column[String]("mobile")
    def email=column[String]("email")
    def * = (uID.? , uName , password , mobile , email) <> (NewUser.tupled , NewUser.unapply)
  }

class UserRepositoryImpl extends UserRepository {
  implicit val ec = StartApp.executionContext
  protected lazy val UserTable = TableQuery[UserTable]


  override def authenticateUser(uName: String):DBIO[Option[NewUser]] = {
    UserTable.filter(_.uName === uName).result.headOption
  }


  override def createUser(user: NewUser) : DBIO[Int]  = {
    UserTable returning UserTable.map(_.uID)+= user
  }
}



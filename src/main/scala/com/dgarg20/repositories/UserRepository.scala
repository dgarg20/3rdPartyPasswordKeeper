package com.dgarg20.repositories

import com.dgarg20.models.NewUser
import slick.dbio.DBIO

/**
  * Created by deepanshu.garg@yatraonline.local on 2/3/17.
  */
trait UserRepository {
  def createUser(user: NewUser) : DBIO[Int]
  def authenticateUser(uName: String ) : DBIO[Option[NewUser]]
}

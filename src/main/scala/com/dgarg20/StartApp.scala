package com.dgarg20

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.dgarg20.controllers.{KeeperController, UserController}

import scala.io.StdIn

/**
  * Created by deepanshu.garg@yatraonline.local on 23/2/17.
  */
object StartApp extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route=new KeeperController().route ~ new UserController().route

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8085)

  println(s"Server online at http://localhost:8085/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}

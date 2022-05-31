package com.myorg.myservice

import com.myorg.myservice.controllers.CreateEventsController
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {
    val appContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val controller = appContext.getBean(CreateEventsController::class.java)
    controller.run()
}

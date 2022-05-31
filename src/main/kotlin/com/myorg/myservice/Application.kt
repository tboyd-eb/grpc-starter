package com.myorg.myservice

import io.grpc.Server
import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main() {
    val appContext = AnnotationConfigApplicationContext(AppConfig::class.java)
    val server = appContext.getBean(Server::class.java)
    println("Here")
    server.start()
    Runtime.getRuntime().addShutdownHook(
        Thread {
            server.shutdown()
        }
    )
    server.awaitTermination()
}

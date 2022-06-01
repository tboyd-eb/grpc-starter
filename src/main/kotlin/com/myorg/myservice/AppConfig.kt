package com.myorg.myservice

import com.myorg.myservice.controllers.CreateEventsController
import com.myorg.myservice.validators.CreateEventsValidator
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import org.springframework.context.annotation.Bean

const val minGeneratedEventIdValueInclusive = 1UL

class AppConfig {
    @Bean
    fun createEventsController(
        createEventsValidator: CreateEventsValidator,
        eventsRepository: EventsRepository,
    ): CreateEventsController {
        return CreateEventsController(
            validator = createEventsValidator,
            repo = eventsRepository,
            minGeneratedEventIdValueInclusive =
            minGeneratedEventIdValueInclusive,
            maxGeneratedEventIdValueInclusive = ULong.MAX_VALUE,
        )
    }

    @Bean
    fun createEventsValidator(): CreateEventsValidator {
        return CreateEventsValidator()
    }

    @Bean
    fun eventsRepository(): EventsRepository {
        return EventsRepository()
    }

    @Bean
    fun myService(
        createEventsController: CreateEventsController
    ): MyService {
        return MyService(createEventsController)
    }

    @Bean
    fun server(myService: MyService): Server {
        val port = 50051
        return ServerBuilder
            .forPort(port)
            .addService(myService)
            .addService(ProtoReflectionService.newInstance())
            .build()
    }
}

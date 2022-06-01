package com.myorg.myservice.controllers

import com.myorg.myservice.EventsRepository
import com.myorg.myservice.proto.GetComplimentsResponse
import com.myorg.myservice.validators.CreateEventsValidator

class CreateEventsController(
    validator: CreateEventsValidator,
    repo: EventsRepository,
    minGeneratedEventIdValueInclusive: ULong,
    maxGeneratedEventIdValueInclusive: ULong,
) {
    fun run(): GetComplimentsResponse {
        return GetComplimentsResponse
            .newBuilder()
            .addAllCompliments(listOf("Good job!", "You rock!", "Nicely done!"))
            .build()
    }
}

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
        val builder = GetComplimentsResponse.newBuilder()
        builder.addAllCompliments(
            listOf("Good job!", "You rock!", "Nicely done!")
        )
        return builder.build()
    }
}

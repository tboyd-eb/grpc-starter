package com.myorg.myservice.controllers

import com.myorg.myservice.validators.CreateEventsValidator
import com.myorg.myservice.EventsRepository

open class CreateEventsController {
    constructor(
        validator: CreateEventsValidator,
        repo: EventsRepository,
        minGeneratedEventIdValueInclusive: ULong,
        maxGeneratedEventIdValueInclusive: ULong,
    ) {
        // TODO: something
    }

    fun run() {
        println("Hello, Eventbrite!")
    }
}

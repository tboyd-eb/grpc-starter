package com.myorg.myservice.controllers

import com.myorg.myservice.EventsRepository
import com.myorg.myservice.proto.GetComplimentsResponse
import com.myorg.myservice.validators.CreateEventsValidator
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

class CreateEventsControllerTest : DescribeSpec({
    lateinit var mockCreateEventsValidator: CreateEventsValidator
    lateinit var mockEventsRepository: EventsRepository
    lateinit var createEventsController: CreateEventsController

    beforeEach {
        mockCreateEventsValidator = mockk(relaxed = true)
        mockEventsRepository = mockk(relaxed = true)
        createEventsController = CreateEventsController(
            mockCreateEventsValidator,
            mockEventsRepository,
            1UL,
            ULong.MAX_VALUE,
        )
    }

    describe("CreateEventsController") {
        it("should produce a list of compliments") {
            val expectedResponse = GetComplimentsResponse
                .newBuilder()
                .addAllCompliments(
                    listOf("Good job!", "You rock!", "Nicely done!")
                )
                .build()
            val actualResponse = createEventsController.run()

            actualResponse shouldBe expectedResponse
        }
    }
})

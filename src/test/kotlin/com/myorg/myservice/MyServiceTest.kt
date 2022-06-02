package com.myorg.myservice

import com.myorg.myservice.controllers.CreateEventsController
import com.myorg.myservice.proto.GetComplimentsRequest
import com.myorg.myservice.proto.GetComplimentsResponse
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.mockk

class MyServiceTest : DescribeSpec({
    lateinit var myService: MyService
    lateinit var mockCreateEventsController: CreateEventsController

    beforeEach {
        mockCreateEventsController = mockk(relaxed = true)
        myService = MyService(mockCreateEventsController)
    }

    describe("GetCompliments endpoint") {
        it("should respond") {
            val response = myService.getCompliments(
                GetComplimentsRequest.getDefaultInstance()
            )

            response shouldNotBe null
        }
    }

    describe("CreateEventsController") {
        it("should produce a list of compliments") {
            val expectedResponse = GetComplimentsResponse
                .newBuilder()
                .addAllCompliments(
                    listOf("Good job!", "You rock!", "Nicely done!")
                )
                .build()
            val request = GetComplimentsRequest.getDefaultInstance()

            coEvery {
                myService.getCompliments(request)
            } returns expectedResponse
        }
    }
})

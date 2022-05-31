package com.myorg.myservice

import com.myorg.myservice.controllers.CreateEventsController
import com.myorg.myservice.proto.GetComplimentsRequest
import com.myorg.myservice.proto.GetComplimentsResponse
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.coEvery
import io.mockk.mockk

class MyServiceTest : DescribeSpec({
    lateinit var myService: MyService
    lateinit var mockCreateEventsController: CreateEventsController

    beforeEach {
        mockCreateEventsController = mockk()
        myService = MyService(mockCreateEventsController)
    }

    describe("GetCompliments endpoint") {
        it("should respond with a list of compliments") {
            val expectedResponse = GetComplimentsResponse.newBuilder()
                .addAllCompliments(listOf("Good job!", "You rock!", "Nicely done!"))
                .build()

            val request = GetComplimentsRequest.newBuilder().build()

            coEvery { myService.getCompliments(request) } returns expectedResponse
        }
    }
})

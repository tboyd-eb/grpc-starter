package com.myorg.myservice

import com.myorg.myservice.controllers.CreateEventsController
import com.myorg.myservice.proto.GetComplimentsRequest
import com.myorg.myservice.proto.GetComplimentsResponse
import com.myorg.myservice.proto.MyServiceGrpcKt

class MyService(
    private val createEventsController: CreateEventsController,
) : MyServiceGrpcKt.MyServiceCoroutineImplBase() {
    override suspend fun getCompliments(
        request: GetComplimentsRequest
    ): GetComplimentsResponse {
        return createEventsController.run()
    }
}

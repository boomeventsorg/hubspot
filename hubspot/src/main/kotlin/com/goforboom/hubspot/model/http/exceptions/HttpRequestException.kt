package com.goforboom.hubspot.model.http.exceptions

import com.goforboom.hubspot.exceptions.HubSpotException

class HttpRequestException(
    val responseCode: Int,
    val responseMessage: String,
    override val message: String = "Unknown HTTP error with code '$responseCode' and message '$responseMessage' received."
) : HubSpotException(message)

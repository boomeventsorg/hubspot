package org.boomevents.hubspot.model.http.exceptions

import org.boomevents.hubspot.exceptions.HubSpotException

class HttpRequestException(
    private val responseCode: Int,
    private val responseMessage: String,
    override val message: String = "Unknown HTTP error with code '$responseCode' and message '$responseMessage' received."
) : HubSpotException(message)

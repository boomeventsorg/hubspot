package com.goforboom.hubspot.exceptions

abstract class HubSpotException(override val message: String) : RuntimeException(message)

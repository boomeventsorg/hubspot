package org.boomevents.hubspot.exceptions

abstract class HubSpotException(override val message: String) : RuntimeException(message)

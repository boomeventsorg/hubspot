package com.goforboom.hubspot.domain.customobject.exceptions

import com.goforboom.hubspot.exceptions.HubSpotException

abstract class CustomObjectException(override val message: String) : HubSpotException(message)

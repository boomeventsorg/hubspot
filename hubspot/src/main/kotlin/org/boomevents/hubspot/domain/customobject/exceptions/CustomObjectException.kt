package org.boomevents.hubspot.domain.customobject.exceptions

import org.boomevents.hubspot.exceptions.HubSpotException

abstract class CustomObjectException(override val message: String) : HubSpotException(message)

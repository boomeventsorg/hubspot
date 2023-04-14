package org.boomevents.hubspot.domain.deals.exceptions

import org.boomevents.hubspot.exceptions.HubSpotException

abstract class DealException(override val message: String) : HubSpotException(message)

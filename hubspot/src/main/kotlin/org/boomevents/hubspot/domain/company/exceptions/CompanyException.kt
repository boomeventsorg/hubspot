package org.boomevents.hubspot.domain.company.exceptions

import org.boomevents.hubspot.exceptions.HubSpotException

abstract class CompanyException(override val message: String) : HubSpotException(message)

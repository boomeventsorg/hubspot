package com.goforboom.hubspot.domain.company.exceptions

import com.goforboom.hubspot.exceptions.HubSpotException

abstract class CompanyException(override val message: String) : HubSpotException(message)

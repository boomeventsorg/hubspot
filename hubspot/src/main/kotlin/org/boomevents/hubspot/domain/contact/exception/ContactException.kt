package org.boomevents.hubspot.domain.contact.exception

import org.boomevents.hubspot.exceptions.HubSpotException

abstract class ContactException(override val message: String) : HubSpotException(message)

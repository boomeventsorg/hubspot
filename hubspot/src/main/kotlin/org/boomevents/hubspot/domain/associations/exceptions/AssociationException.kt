package org.boomevents.hubspot.domain.associations.exceptions

import org.boomevents.hubspot.exceptions.HubSpotException

abstract class AssociationException(override val message: String) : HubSpotException(message)

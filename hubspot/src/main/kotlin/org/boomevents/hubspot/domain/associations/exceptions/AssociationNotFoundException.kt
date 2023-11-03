package org.boomevents.hubspot.domain.deals.exceptions

import org.boomevents.hubspot.domain.associations.exceptions.AssociationException
import java.math.BigInteger

class AssociationNotFoundException(
    associationId: BigInteger,
    override val message: String = "Association '$associationId' was not found."
) : AssociationException(message)

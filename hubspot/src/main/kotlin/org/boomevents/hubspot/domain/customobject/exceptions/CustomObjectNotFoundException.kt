package org.boomevents.hubspot.domain.customobject.exceptions

import java.math.BigInteger

class CustomObjectNotFoundException(
    customObjectEntity: String,
    customObjectId: BigInteger,
    override val message: String = "Custom object '$customObjectId' was not found in table '$customObjectEntity'."
) : CustomObjectException(message)

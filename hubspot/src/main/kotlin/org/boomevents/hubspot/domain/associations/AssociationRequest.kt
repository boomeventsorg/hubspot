package org.boomevents.hubspot.domain.associations

import java.math.BigInteger

data class AssociationRequest(
    val fromObjectType: AssociationObjectType,
    val fromObjectId: BigInteger,
    val toObjectType: AssociationObjectType,
    val toObjectId: BigInteger) {
}

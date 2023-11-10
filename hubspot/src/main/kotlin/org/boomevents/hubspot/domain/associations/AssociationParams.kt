package org.boomevents.hubspot.domain.associations

import java.math.BigInteger

data class AssociationParams(
    val associationTypeCategory: AssociationTypeCategory,
    val associationTypeId: Int) {
}

package org.boomevents.hubspot.domain.deals.exceptions

import org.boomevents.hubspot.domain.associations.exceptions.AssociationException
import org.boomevents.hubspot.model.mapper.Mapper
import java.math.BigInteger

data class AssociationErrorDetails(
    val objectType: String,
    val objectId: String
)

class AssociationNotFoundException(
    val objectType: String,
    val objectId: String
) : AssociationException("Association error: $objectType with ID $objectId was not found in Hubspot.") {
    companion object {
        fun fromJson(json: String): AssociationNotFoundException {
            val errorDetails = Mapper.mapToObject<AssociationErrorDetails>(json)
            return AssociationNotFoundException(errorDetails.objectType, errorDetails.objectId)
        }
    }
}

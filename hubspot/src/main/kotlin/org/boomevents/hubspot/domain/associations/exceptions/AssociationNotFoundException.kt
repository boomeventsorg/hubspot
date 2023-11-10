package org.boomevents.hubspot.domain.associations.exceptions

import kong.unirest.JsonNode
import org.boomevents.hubspot.model.mapper.Mapper

data class AssociationErrorDetails(
    val objectType: String,
    val objectId: String
)

class AssociationNotFoundException(objectType: String, objectId: String) : AssociationException("Association error: $objectType with ID $objectId was not found in Hubspot.") {
    companion object {
        fun fromJson(json: JsonNode): AssociationNotFoundException {
            val errorDetails = Mapper.mapToObject<AssociationErrorDetails>(json)
            return AssociationNotFoundException(errorDetails.objectType, errorDetails.objectId)
        }
    }
}

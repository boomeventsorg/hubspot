package org.boomevents.hubspot.domain.associations

import org.boomevents.hubspot.model.http.Requester
import org.boomevents.hubspot.Client
import org.boomevents.hubspot.ClientRequestCatalog
import org.boomevents.hubspot.domain.associations.exceptions.AssociationException
import org.boomevents.hubspot.domain.associations.exceptions.AssociationNotFoundException.Companion.fromJson
import org.boomevents.hubspot.model.http.RequestMethod
import org.boomevents.hubspot.model.http.exceptions.HttpRequestException
import org.boomevents.hubspot.model.mapper.Mapper

class AssociationClient(private val hubSpotClient: Client) {

    private fun buildUrlForAssociationRecord(url: String, associationRequest: AssociationRequest): String {
        return url
            .replace("{fromObjectType}", associationRequest.fromObjectType.toString())
            .replace("{fromObjectId}", associationRequest.fromObjectId.toString())
            .replace("{toObjectType}", associationRequest.toObjectType.toString())
            .replace("{toObjectId}", associationRequest.toObjectId.toString())
    }

    /**
     * Creates an association between two objects with a default association type.
     */
    @Throws(
        AssociationException::class,
        HttpRequestException::class
    )
    fun createDefaultAssociation(request: AssociationRequest): AssociationResult {
        val response = Requester.requestJson(
            hubSpotClient,
            RequestMethod.PUT,
            buildUrlForAssociationRecord(ClientRequestCatalog.V4.OBJECT_DEFAULT_ASSOCIATION, request),
            emptyMap(),
            request)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        }  else {
            when (response.status) {
                404 -> throw fromJson(response.body)
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }
}

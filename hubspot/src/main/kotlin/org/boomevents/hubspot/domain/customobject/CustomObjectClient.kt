package org.boomevents.hubspot.domain.customobject

import org.boomevents.hubspot.model.http.RequestMethod
import org.boomevents.hubspot.model.http.Requester
import org.boomevents.hubspot.Client
import org.boomevents.hubspot.ClientRequestCatalog
import org.boomevents.hubspot.domain.customobject.exceptions.CustomObjectNotFoundException
import org.boomevents.hubspot.model.http.exceptions.HttpRequestException
import org.boomevents.hubspot.model.mapper.Mapper
import java.math.BigInteger

class CustomObjectClient(
    private val hubSpotClient: Client,
    private val customObjectEntity: String
) {

    private fun buildUrlForObjectRecord(customObjectId: BigInteger): String {
        return ClientRequestCatalog.V3.CUSTOM_OBJECT_DETAIL
            .replace("{customObjectEntity}", customObjectEntity)
            .replace(
                "{customObjectId}", customObjectId.toString()
            )
    }


    fun <P> createCustomObjectRecord(request: CustomObjectRequest<P>): CustomObject {
        val requestUrl = ClientRequestCatalog.V3.CUSTOM_OBJECT.replace("{customObjectEntity}", customObjectEntity)

        val response = Requester.requestJson(hubSpotClient, RequestMethod.POST, requestUrl, emptyMap(), request)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.body.toPrettyString())
        }
    }


    @Throws(
        CustomObjectNotFoundException::class,
        HttpRequestException::class
    )
    fun findCustomObjectRecord(customObjectId: BigInteger): CustomObject {
        val requestUrl = this.buildUrlForObjectRecord(customObjectId)
        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, requestUrl)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            when (response.status) {
                404 -> throw CustomObjectNotFoundException(this.customObjectEntity, customObjectId)
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }


    fun <P> changeCustomObjectRecord(customObjectId: BigInteger, request: CustomObjectRequest<P>): CustomObject {
        val requestUrl = this.buildUrlForObjectRecord(customObjectId)
        val response = Requester.requestJson(hubSpotClient, RequestMethod.PATCH, requestUrl, emptyMap(), request)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            when (response.status) {
               404 -> throw CustomObjectNotFoundException(this.customObjectEntity, customObjectId)
               else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }

    @Throws(
        HttpRequestException::class
    )
    fun removeCustomObjectRecord(customObjectId: BigInteger) {
        val requestUrl = this.buildUrlForObjectRecord(customObjectId)

        // Unknown company returns HTTP code 204
        val response = Requester.requestVoid(hubSpotClient, RequestMethod.DELETE, requestUrl)

        if (!response.isSuccess) {
            when (response.status) {
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }
}

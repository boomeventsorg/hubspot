package org.boomevents.hubspot.domain.contact

import org.boomevents.hubspot.domain.contact.exception.ContactNotFoundException
import org.boomevents.hubspot.Client
import org.boomevents.hubspot.ClientRequestCatalog
import org.boomevents.hubspot.model.http.RequestMethod
import org.boomevents.hubspot.model.http.Requester
import org.boomevents.hubspot.model.http.exceptions.HttpRequestException
import org.boomevents.hubspot.model.mapper.Mapper
import java.math.BigInteger
import java.util.Collections.emptyMap

class ContactClient(private val hubSpotClient: Client) {

    fun <P> createContact(request: ContactRequest<P>): Contact {
        val response = Requester.requestJson(
            hubSpotClient,
            RequestMethod.POST,
            ClientRequestCatalog.V3.CONTACTS,
            emptyMap(),
            request
        )

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }
    }

    @Throws(
        ContactNotFoundException::class,
        HttpRequestException::class
    )
    fun findContact(contactId: BigInteger): Contact {
        val requestUrl = ClientRequestCatalog.V3.CONTACTS_DETAIL.replace(
            "{contactId}", contactId.toString()
        )

        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, requestUrl)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            when (response.status) {
                404 -> throw ContactNotFoundException(contactId)
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }

    fun <P> changeContact(contactId: BigInteger, request: ContactRequest<P>): Contact {
        val requestUrl = ClientRequestCatalog.V3.CONTACTS_DETAIL.replace(
            "{contactId}", contactId.toString()
        )

        val response = Requester.requestJson(hubSpotClient, RequestMethod.PATCH, requestUrl, emptyMap(), request)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            when (response.status) {
                404 -> throw ContactNotFoundException(contactId)
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }

    @Throws(
        HttpRequestException::class
    )
    fun removeContact(contactId: BigInteger) {
        val requestUrl = ClientRequestCatalog.V3.CONTACTS_DETAIL.replace(
            "{contactId}", contactId.toString()
        )

        // Unknown contact returns HTTP code 204
        val response = Requester.requestVoid(hubSpotClient, RequestMethod.DELETE, requestUrl)

        if (!response.isSuccess) {
            when (response.status) {
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }
}

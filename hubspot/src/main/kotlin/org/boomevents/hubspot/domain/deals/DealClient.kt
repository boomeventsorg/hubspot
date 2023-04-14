package org.boomevents.hubspot.domain.deals

import org.boomevents.hubspot.model.http.RequestMethod
import org.boomevents.hubspot.model.http.Requester
import org.boomevents.hubspot.Client
import org.boomevents.hubspot.ClientRequestCatalog
import org.boomevents.hubspot.domain.deals.exceptions.DealsNotFoundException
import org.boomevents.hubspot.model.http.exceptions.HttpRequestException
import org.boomevents.hubspot.model.mapper.Mapper
import java.math.BigInteger

class DealClient(private val hubSpotClient: Client) {

    fun <P> createDeal(request: DealRequest<P>): Deal {
        val response = Requester.requestJson(hubSpotClient, RequestMethod.POST, ClientRequestCatalog.V3.DEALS, emptyMap(), request)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }
    }


    @Throws(
        DealsNotFoundException::class,
        HttpRequestException::class
    )
    fun findDeal(dealId: BigInteger): Deal {
        val requestUrl = ClientRequestCatalog.V3.DEALS_DETAIL.replace(
            "{dealId}", dealId.toString()
        )

        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, requestUrl)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            when (response.status) {
                404 -> throw DealsNotFoundException(dealId)
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }


    fun <P> changeDeal(dealId: BigInteger, request: DealRequest<P>): Deal {
        val requestUrl = ClientRequestCatalog.V3.DEALS_DETAIL.replace(
            "{dealId}", dealId.toString()
        )

        val response = Requester.requestJson(hubSpotClient, RequestMethod.PATCH, requestUrl, emptyMap(), request)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            when (response.status) {
               404 -> throw DealsNotFoundException(dealId)
               else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }

    @Throws(
        HttpRequestException::class
    )
    fun removeDeal(dealId: BigInteger) {
        val requestUrl = ClientRequestCatalog.V3.DEALS_DETAIL.replace(
            "{dealId}", dealId.toString()
        )

        // Unknown deal returns HTTP code 204
        val response = Requester.requestVoid(hubSpotClient, RequestMethod.DELETE, requestUrl)

        if (!response.isSuccess) {
            when (response.status) {
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }
}

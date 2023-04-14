package org.boomevents.hubspot.domain.company

import org.boomevents.hubspot.model.http.RequestMethod
import org.boomevents.hubspot.model.http.Requester
import org.boomevents.hubspot.Client
import org.boomevents.hubspot.ClientRequestCatalog
import org.boomevents.hubspot.domain.company.exceptions.CompanyNotFoundException
import org.boomevents.hubspot.model.http.exceptions.HttpRequestException
import org.boomevents.hubspot.model.mapper.Mapper
import java.math.BigInteger

class CompanyClient(private val hubSpotClient: Client) {

    fun <P> createCompany(request: CompanyRequest<P>): Company {
        val response = Requester.requestJson(hubSpotClient, RequestMethod.POST, ClientRequestCatalog.V3.COMPANIES, emptyMap(), request)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }
    }


    @Throws(
        CompanyNotFoundException::class,
        HttpRequestException::class
    )
    fun findCompany(companyId: BigInteger): Company {
        val requestUrl = ClientRequestCatalog.V3.COMPANIES_DETAIL.replace(
            "{companyId}", companyId.toString()
        )

        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, requestUrl)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            when (response.status) {
                404 -> throw CompanyNotFoundException(companyId)
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }


    fun <P> changeCompany(companyId: BigInteger, request: CompanyRequest<P>): Company {
        val requestUrl = ClientRequestCatalog.V3.COMPANIES_DETAIL.replace(
            "{companyId}", companyId.toString()
        )

        val response = Requester.requestJson(hubSpotClient, RequestMethod.PATCH, requestUrl, emptyMap(), request)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            when (response.status) {
               404 -> throw CompanyNotFoundException(companyId)
               else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }

    @Throws(
        HttpRequestException::class
    )
    fun removeCompany(companyId: BigInteger) {
        val requestUrl = ClientRequestCatalog.V3.COMPANIES_DETAIL.replace(
            "{companyId}", companyId.toString()
        )

        // Unknown company returns HTTP code 204
        val response = Requester.requestVoid(hubSpotClient, RequestMethod.DELETE, requestUrl)

        if (!response.isSuccess) {
            when (response.status) {
                else -> throw HttpRequestException(response.status, response.statusText)
            }
        }
    }
}

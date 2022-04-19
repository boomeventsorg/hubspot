package com.goforboom.hubspot.domain.company

import com.goforboom.hubspot.model.http.RequestMethod
import com.goforboom.hubspot.model.http.Requester
import com.goforboom.hubspot.Client
import com.goforboom.hubspot.ClientRequestCatalog
import com.goforboom.hubspot.domain.company.exceptions.CompanyNotFoundException
import com.goforboom.hubspot.model.http.exceptions.HttpRequestException
import com.goforboom.hubspot.model.mapper.Mapper
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

}

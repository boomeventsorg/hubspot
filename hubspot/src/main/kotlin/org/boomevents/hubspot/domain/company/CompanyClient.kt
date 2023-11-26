package org.boomevents.hubspot.domain.company

import com.fasterxml.jackson.databind.JsonNode
import org.boomevents.hubspot.model.http.RequestMethod
import org.boomevents.hubspot.model.http.Requester
import org.boomevents.hubspot.Client
import org.boomevents.hubspot.ClientRequestCatalog
import org.boomevents.hubspot.domain.company.exceptions.CompanyNotFoundException
import org.boomevents.hubspot.model.http.exceptions.HttpRequestException
import org.boomevents.hubspot.model.mapper.Mapper
import org.boomevents.hubspot.model.mapper.Mapper.objectMapper
import java.math.BigInteger

class CompanyClient(private val hubSpotClient: Client) {

    companion object {
        const val COMPANIES_PER_PAGE = 100
    }

    fun <P> createCompany(request: CompanyRequest<P>): Company {
        val response = Requester.requestJson(hubSpotClient, RequestMethod.POST, ClientRequestCatalog.V3.COMPANIES, emptyMap(), request)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }
    }

    @Throws(
        HttpRequestException::class
    )
    /**
     * @return List of companies
     */
    fun getCompanies(): List<Company> {
        var after: String? = null
        val allCompanies = mutableListOf<Company>()

        do {
            val url = buildUrl(after)
            val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, url)

            if (response.isSuccess) {
                val page = Mapper.mapToList<Company>(response.body)
                allCompanies.addAll(page)

                val nextAfter = extractNextAfter(response.body.toString())
                after = nextAfter
            } else {
                throw RuntimeException("Failed to fetch companies: ${response.statusText}")
            }
        } while (after != null)

        return allCompanies
    }

    private fun buildUrl(after: String?): String {
        val baseUrl = ClientRequestCatalog.V3.COMPANIES
        return "$baseUrl?limit=$COMPANIES_PER_PAGE" + (after?.let { "&after=$it" } ?: "")
    }

    private fun extractNextAfter(json: String): String? {
        val rootNode: JsonNode = objectMapper.readTree(json)
        return rootNode.path("paging").path("next").path("after").asText(null)
    }

    @Throws(
        CompanyNotFoundException::class,
        HttpRequestException::class
    )
    fun findCompany(companyId: BigInteger): Company {
        val requestUrl = ClientRequestCatalog.V3.COMPANIES_DETAIL.replace(
            "{companyId}", companyId.toString()
        )
        return makeRequestFindCompany(requestUrl)
    }

    @Throws(
        CompanyNotFoundException::class,
        HttpRequestException::class
    )
    fun findCompanyByDomain(domain: String): Company {
        val requestUrl = ClientRequestCatalog.V3.COMPANIES_DETAIL.replace(
            "{domain}", domain
        ) + "?idProperty=domain"
        return makeRequestFindCompany(requestUrl)
    }

    @Throws(
        CompanyNotFoundException::class,
        HttpRequestException::class
    )
    fun findCompanyByName(name: String): Company {
        val requestUrl = ClientRequestCatalog.V3.COMPANIES_DETAIL.replace(
            "{name}", name
        ) + "?idProperty=name"
        return makeRequestFindCompany(requestUrl)
    }

    @Throws(
        CompanyNotFoundException::class,
        HttpRequestException::class
    )
    fun findCompanyByProperty(propertyName: String, propertyValue: String): Company {
        val requestUrl = ClientRequestCatalog.V3.COMPANIES_DETAIL.replace(
            "{companyId}", propertyValue
        ) + "?idProperty=$propertyName"
        return makeRequestFindCompany(requestUrl)
    }

    private fun makeRequestFindCompany(requestUrl: String): Company {
        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, requestUrl)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            when (response.status) {
                404 -> throw CompanyNotFoundException()
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

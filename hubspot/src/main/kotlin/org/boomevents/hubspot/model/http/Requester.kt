package org.boomevents.hubspot.model.http

import kong.unirest.*
import kong.unirest.jackson.JacksonObjectMapper
import org.boomevents.hubspot.Client
import org.boomevents.hubspot.model.mapper.Mapper

object Requester {

    /** Build default HTTP client with predefined security headers and request method */
    private fun builder(client: Client, method: RequestMethod, path: String): HttpRequest<*> {
        val apiPath = "${client.apiBasePath}$path"

        val request = when (method) {
            RequestMethod.GET -> Unirest.get(apiPath)
            RequestMethod.POST -> Unirest.post(apiPath)
            RequestMethod.PATCH -> Unirest.patch(apiPath)
            RequestMethod.DELETE -> Unirest.delete(apiPath)
            RequestMethod.PUT -> Unirest.put(apiPath)
        }

        // Associate default headers used in every request
        // https://developers.hubspot.com/docs/api/private-apps#make-api-calls-with-your-app-s-access-token
        request
            .header("Authorization", "Bearer ${client.apiKey}")
            .withObjectMapper(
                CustomObjectMapper()
            )

        return request
    }


    /** Request JSON endpoint */
    fun requestJson(
        client: Client,
        method: RequestMethod,
        path: String,
        query: Map<String, String> = emptyMap(),
        body: Any? = null
    ): HttpResponse<JsonNode> {
        val request = builder(client, method, path)
            .header("Content-Type", "application/json")
            .queryString(query)

        if (request is HttpRequestWithBody && body !== null) {
            return request.body(body).asJson()
        }

        return request.asJson()
    }


    /** Request void endpoint (without any response data) */
    fun requestVoid(
        client: Client,
        method: RequestMethod,
        path: String,
        query: Map<String, String> = emptyMap(),
        body: Any? = null
    ): HttpResponse<*> {
        val request = builder(client, method, path)
            .header("Content-Type", "application/json")
            .queryString(query)

        if (request is HttpRequestWithBody && body !== null) {
            request.body(body).asEmpty()
        }

        return request.asEmpty()
    }
}

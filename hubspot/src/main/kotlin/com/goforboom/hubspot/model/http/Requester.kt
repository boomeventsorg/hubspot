package com.goforboom.hubspot.model.http

import kong.unirest.*
import kong.unirest.jackson.JacksonObjectMapper
import com.goforboom.hubspot.Client
import com.goforboom.hubspot.model.mapper.Mapper

object Requester {

    /** Build default HTTP client with predefined security headers and request method */
    private fun builder(client: Client, method: RequestMethod, path: String): HttpRequest<*> {
        val apiPath = "${client.apiBasePath}$path"

        val request = when (method) {
            RequestMethod.GET -> Unirest.get(apiPath)
            RequestMethod.POST -> Unirest.post(apiPath)
            RequestMethod.PATCH -> Unirest.patch(apiPath)
        }

        // Associate default headers used in every request
        // https://developers.hubspot.com/docs/api/intro-to-auth
        request
            .queryString("hapikey", client.apiKey)
            .withObjectMapper(
                JacksonObjectMapper(Mapper.objectMapper)
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
        Unirest.config().objectMapper = JacksonObjectMapper()

        val request = builder(client, method, path)
            .header("Content-Type", "application/json")
            .queryString(query)

        if (request is HttpRequestWithBody && body !== null) {
            return request.body(body).asJson()
        }

        return request.asJson()
    }
}

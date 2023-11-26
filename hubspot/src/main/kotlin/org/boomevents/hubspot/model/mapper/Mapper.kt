package org.boomevents.hubspot.model.mapper

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kong.unirest.JsonNode
import org.boomevents.hubspot.model.http.CustomObjectMapper

object Mapper {

    val objectMapper: ObjectMapper = CustomObjectMapper().jacksonMapper

    inline fun <reified R> mapToObject(jsonNode: JsonNode): R {
        return objectMapper.readValue(jsonNode.toString(), object : TypeReference<R>() {})
    }

    inline fun <reified R> mapToList(jsonNode: JsonNode): List<R> {
        val resultsNode = jsonNode.`object`["results"]
        return objectMapper.readValue(resultsNode.toString(), object : TypeReference<List<R>>() {})
    }
}

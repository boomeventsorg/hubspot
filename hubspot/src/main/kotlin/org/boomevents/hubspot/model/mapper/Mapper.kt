package org.boomevents.hubspot.model.mapper

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import kong.unirest.JsonNode

object Mapper {

    val objectMapper: ObjectMapper = ObjectMapper()
        .registerModule(
            JavaTimeModule() // Support Java 8 modern datetime
        )
        .registerModule(
            KotlinModule()
        )

    inline fun <reified R> mapToObject(jsonNode: JsonNode): R {
        return objectMapper.readValue(jsonNode.toString(), object : TypeReference<R>() {})
    }

    inline fun <reified R> mapToList(jsonNode: JsonNode): List<R> {
        val resultsNode = jsonNode.`object`.get("results")
        return objectMapper.readValue(resultsNode.toString(), object : TypeReference<List<R>>() {})
    }
}

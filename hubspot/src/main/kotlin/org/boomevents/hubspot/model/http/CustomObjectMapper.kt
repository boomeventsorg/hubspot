package org.boomevents.hubspot.model.http

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class CustomObjectMapper : kong.unirest.ObjectMapper {
    internal val jacksonMapper: com.fasterxml.jackson.databind.ObjectMapper = jacksonObjectMapper()

    init {
        // Configure your Jackson ObjectMapper here
        jacksonMapper.registerModule(
            KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .configure(KotlinFeature.StrictNullChecks, false)
                .build()
        )
        .registerModule(JavaTimeModule())
    }

    override fun writeValue(value: Any): String {
        return jacksonMapper.writeValueAsString(value)
    }

    override fun <T> readValue(value: String, valueType: Class<T>): T {
        return jacksonMapper.readValue(value, valueType)
    }
}

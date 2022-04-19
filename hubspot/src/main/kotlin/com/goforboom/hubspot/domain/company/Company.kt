package com.goforboom.hubspot.domain.company

import java.math.BigInteger
import java.time.LocalDateTime

class Company(
    val id: BigInteger,

    // Jackson is not able to recognize generic in generic,
    // it means he is not able to cast two levels of generic (company and custom company props)
    val properties: Map<String, Any>,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val archived: Boolean
)

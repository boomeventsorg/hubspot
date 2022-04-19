package com.goforboom.hubspot.domain.company

import java.math.BigInteger
import java.time.LocalDateTime

class Company<P>(
    val id: BigInteger,
    val properties: Map<String, Any>,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val archived: Boolean
)

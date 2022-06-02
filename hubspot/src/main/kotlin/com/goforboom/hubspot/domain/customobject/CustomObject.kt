package com.goforboom.hubspot.domain.customobject

import com.goforboom.hubspot.domain.DataEntity
import java.math.BigInteger
import java.time.LocalDateTime

class CustomObject(
    override val id: BigInteger,
    override val properties: Map<String, Any>,
    override val createdAt: LocalDateTime,
    override val updatedAt: LocalDateTime,
    override val archived: Boolean
) : DataEntity




package org.boomevents.hubspot.domain.deals

import org.boomevents.hubspot.domain.DataEntity
import java.math.BigInteger
import java.time.LocalDateTime

class Deal(
    override val id: BigInteger,
    override val properties: Map<String, Any>,
    override val createdAt: LocalDateTime,
    override val updatedAt: LocalDateTime,
    override val archived: Boolean
) : DataEntity

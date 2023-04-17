package main.kotlin.org.boomevents.hubspot.domain.contact

import org.boomevents.hubspot.domain.DataEntity
import java.math.BigInteger
import java.time.LocalDateTime

class Contact(
    override val id: BigInteger,
    override val properties: Map<String, Any>,
    override val createdAt: LocalDateTime,
    override val updatedAt: LocalDateTime,
    override val archived: Boolean
) : DataEntity

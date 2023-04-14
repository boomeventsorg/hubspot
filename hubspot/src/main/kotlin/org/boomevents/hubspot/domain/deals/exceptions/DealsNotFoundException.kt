package org.boomevents.hubspot.domain.deals.exceptions

import java.math.BigInteger

class DealsNotFoundException(
    dealId: BigInteger,
    override val message: String = "Deals '$dealId' was not found."
) : DealException(message)

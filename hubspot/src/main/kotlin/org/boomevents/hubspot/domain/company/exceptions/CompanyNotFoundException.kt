package org.boomevents.hubspot.domain.company.exceptions

import java.math.BigInteger

class CompanyNotFoundException(
    companyId: BigInteger,
    override val message: String = "Company '$companyId' was not found."
) : CompanyException(message)

package org.boomevents.hubspot.domain.company.exceptions

import java.math.BigInteger

class CompanyNotFoundException(
    companyId: BigInteger? = null
) : CompanyException(
    message = "Company ${companyId?.let { "'$it'" } ?: "was not found."}"
)

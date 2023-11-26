package org.boomevents.hubspot.domain.company.exceptions

class CompanyNotFoundException(
    companyId: Any,
    override val message: String = "Company '$companyId' was not found."
) : CompanyException(message)

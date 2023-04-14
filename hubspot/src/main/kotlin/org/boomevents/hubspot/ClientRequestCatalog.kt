package org.boomevents.hubspot

object ClientRequestCatalog {

    object V3 {
        const val COMPANIES = "/crm/v3/objects/companies"
        const val COMPANIES_DETAIL = "/crm/v3/objects/companies/{companyId}"

        const val CUSTOM_OBJECT = "/crm/v3/objects/{customObjectEntity}"
        const val CUSTOM_OBJECT_DETAIL = "/crm/v3/objects/{customObjectEntity}/{customObjectId}"
    }
}

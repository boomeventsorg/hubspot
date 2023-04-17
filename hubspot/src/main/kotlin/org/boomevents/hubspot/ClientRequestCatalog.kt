package org.boomevents.hubspot

object ClientRequestCatalog {

    object V3 {
        const val COMPANIES = "/crm/v3/objects/companies"
        const val COMPANIES_DETAIL = "/crm/v3/objects/companies/{companyId}"

        const val DEALS = "/crm/v3/objects/deals"
        const val DEALS_DETAIL = "/crm/v3/objects/deals/{dealId}"

        const val CUSTOM_OBJECT = "/crm/v3/objects/{customObjectEntity}"
        const val CUSTOM_OBJECT_DETAIL = "/crm/v3/objects/{customObjectEntity}/{customObjectId}"

        const val CONTACTS = "/crm/v3/objects/contacts"
        const val CONTACTS_DETAIL = "/crm/v3/objects/contacts/{contactId}"
    }
}

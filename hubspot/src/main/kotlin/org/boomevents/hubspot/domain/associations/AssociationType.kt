package org.boomevents.hubspot.domain.associations

enum class AssociationType(val value: Int) {
    CONTACT_TO_COMPANY_PRIMARY(1),
    COMPANY_TO_CONTACT_PRIMARY(2),
    CONTACT_TO_COMPANY(279),
    COMPANY_TO_CONTACT(280),
}

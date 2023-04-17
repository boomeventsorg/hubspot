package main.kotlin.org.boomevents.hubspot.domain.contact.exception

import java.math.BigInteger

class ContactNotFoundException(
    contactId: BigInteger,
    override val message: String = "Contact '$contactId' was not found."
) : ContactException(message)

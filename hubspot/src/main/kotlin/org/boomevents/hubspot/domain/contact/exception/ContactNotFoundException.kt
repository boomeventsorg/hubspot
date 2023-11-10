package org.boomevents.hubspot.domain.contact.exception

import java.math.BigInteger

class ContactNotFoundException : ContactException {
    constructor(contactId: BigInteger) : 
        super("Contact '$contactId' was not found.")

    constructor() : 
        super("Contact was not found.")
}

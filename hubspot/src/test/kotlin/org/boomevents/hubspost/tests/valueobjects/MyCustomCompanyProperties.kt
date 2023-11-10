package org.boomevents.hubspost.tests.valueobjects

import com.fasterxml.jackson.annotation.JsonProperty

class MyCustomCompanyProperties(
    val name: String,
    val age: Int,
    val email: String,
    val newsletter: Boolean,

    // You can customize final property name send to HubSpot API
    @JsonProperty("billing_bank_iban")
    val iban: String? = null
)

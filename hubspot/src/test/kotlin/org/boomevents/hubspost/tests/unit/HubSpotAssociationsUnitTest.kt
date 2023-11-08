package org.boomevents.hubspost.tests.unit

import org.boomevents.hubspost.tests.helpers.HubSpotClientProvider
import org.boomevents.hubspost.tests.valueobjects.MyCustomCompanyProperties
import org.boomevents.hubspot.domain.company.CompanyClient
import org.boomevents.hubspot.domain.company.CompanyRequest
import org.junit.Assert
import org.junit.Test


class HubSpotAssociationsUnitTest {

    private val hubspotClient = HubSpotClientProvider.testingClient()


    @Test
    fun testMailFinishedOrderNotification() {
        val companyEmail = "john.doe@example.com"
        val companyAge = 28

        val hubspotResponse = CompanyClient(this.hubspotClient).createCompany(
            CompanyRequest(
                properties = MyCustomCompanyProperties(
                    name = "John Doe",
                    email = companyEmail,
                    age = companyAge,
                    newsletter = false
                )
            )
        )

        Assert.assertEquals(companyEmail, hubspotResponse.properties["email"])
        Assert.assertEquals(companyAge, hubspotResponse.properties["name"])
    }

}

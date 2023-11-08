package org.boomevents.hubspost.tests.helpers

import org.boomevents.hubspot.Client
import java.io.FileInputStream
import java.util.Properties

class HubSpotClientProvider {

    companion object {

        fun testingClient(): Client {
            val properties = Properties()
            val propertiesFile = Thread.currentThread().getContextClassLoader().getResource("application.properties")!!.path

            properties.load(
                FileInputStream(propertiesFile)
            )

            val apiBasePath = properties["tests.hubspotApiBasePath"]
            val apiKey = properties["tests.hubspotApiKey"]

            assert(apiKey is String)
            assert(apiBasePath is String)

            return Client(apiBasePath as String, apiKey as String)
        }
    }

}

# 🤖 HubSpot Kotlin SDK
General implementation of [HubSpot](https://developers.hubspot.com/docs/api/crm/companies) CRM API in tiny Kotlin SDK.

🎈 Currently in progress, send issues or pull requests 🙌🏼

🚀 Install from offical Maven repository with `com.goforboom:hubspot-sdk:$VERSION`<br>
⚠️ GitHub packages is depricated, use official Maven repository

## Supported features
| Feature 	        | List 	 | Read 	 | Create 	 | Change 	 | Delete 	 |
|------------------|--------|--------|----------|----------|----------|
| Company 	        | ❌    	 | ✅    	 | ✅     	  | ✅      	 | ✅      	 |
| Custom objects 	 | ❌    	 | ✅    	 | ✅     	  | ✅      	 | ✅      	 |
| Contact 	        | ❌    	 | ❌    	 | ❌     	  | ❌      	 | ❌      	 |
| Deal    	        | ❌    	 | ❌    	 | ❌     	  | ❌      	 | ❌      	 |

## Supported types

| Type    | Note                                     | Supported |
|---------|------------------------------------------|-----------|
| String  | -                                        | ✅         |
| Boolean | -                                        | ✅         |
| Long    | -                                        | ✅         |
| Int     | -                                        | ✅         |
| Enum    | Use Java String converted                | ❌         |
| Date    | Use Java 8 dates with toString formatter | ❌         |

## Usage Examples

Basic SDK client configuration
```kotlin
// All basic types are supported
class MyCustomCompanyProperties(
    val name: String,
    val age: Int,
    val email: String,
    val newsletter: Boolean,

    // You can customize final property name send to HubSpot API
    @JsonProperty("billing_bank_iban")
    val iban: String? = null
)

val client = Client(
    apiBasePath = "https://api.hubapi.com",

    // Found in HubSpot company management -> Integrations -> API Keys -> Active API Key
    apiKey = "xxx"
)
```

## #️⃣ Company entity

### Create brand-new company
```kotlin
val companyRequest = CompanyRequest(
    properties = MyCustomCompanyProperties(
        name = "John Doe",
        age = 34,
        email = "john.doe@example.com",
        newsletter = true
    )
)

val companyResponse = companiesClient.createCompany(companyRequest)

println(companyResponse.id) // HubSpot company ID
println(companyResponse.properties["name"]) // John Doe
```

### Change existing company
```kotlin
val companyRequest = CompanyRequest(
    properties = MyCustomCompanyProperties(
        name = "John Doe",
        age = 34,
        email = "john.doe@example.com",
        newsletter = true
    )
)

val companyResponse = companiesClient.changeCompany(123456789, companyRequest)

println(companyResponse.id) // HubSpot company ID
println(companyResponse.properties["name"]) // John Doe
```

## #️⃣ Custom objects

### Create brand-new custom object record
```kotlin
val request = CustomObjectRequest(
    properties = MySuperEventProperties(
        name = "Party #2022",
        address = "New York",

        // Date must be formatted as "YYYY-MM-DD"
        dateFrom = LocalDate
            .now()
            .plusDays(10)
            .format(DateTimeFormatter.ISO_LOCAL_DATE),
        dateUntil = LocalDate
            .now()
            .plusDays(15)
            .format(DateTimeFormatter.ISO_LOCAL_DATE),
    )
)

// HubSpot client and name of custom object table
val myEventsClient = CustomObjectClient(client, "events")

val response = myEventsClient.createCustomObjectRecord(request)

println(response.id) // HubSpot ID
println(response.properties["name"]) // Party #2022
```

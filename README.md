# 🤖 HubSpot Kotlin SDK
General implementation of [HubSpot](https://developers.hubspot.com/docs/api/crm/companies) CRM API in tiny Kotlin SDK.

🎈 Currently in progress, send issues or pull requests 🙌🏼

## Supported features
| Feature 	 | List 	 | Read 	 | Write 	 | Change 	 | Delete 	 |
|-----------|--------|--------|---------|----------|----------|
| Company 	 | ✅    	 | ✅    	 | ✅     	 | ✅      	 | ✅      	 |
| Contact 	 | ❌    	 | ❌    	 | ❌     	 | ❌      	 | ❌      	 |
| Deal    	 | ❌    	 | ❌    	 | ❌     	 | ❌      	 | ❌      	 |

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

val companyResponse = companiesClient.createCompany(companyData)

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

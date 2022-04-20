package com.goforboom.hubspot.client.analytics.v2.values

enum class Breakdown(val pathVariable: String) {
    TOTALS("totals"),
    SESSIONS("sessions"),
    SOURCES("sources"),
    GEOLOCATION("geolocation"),
    UTM_CAMPAIGNS("utm-campaigns"),
    UTM_CONTENTS("utm-contents"),
    UTM_MEDIUMS("utm-mediums"),
    UTM_SOURCES("utm-sources"),
    UTM_TERMS("utm-terms"),
    ;

}

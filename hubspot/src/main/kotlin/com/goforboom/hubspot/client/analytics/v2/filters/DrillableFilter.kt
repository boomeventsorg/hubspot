package com.goforboom.hubspot.client.analytics.v2.filters

interface DrillableFilter {
    val firstDrilldown: Any?
    val secondDrilldown: Any?
}

package com.goforboom.hubspot.client.analytics.v2.filters

data class EventsBatchFilter(
    val ids: List<String>,
    val includeDeletes: Boolean?,
)

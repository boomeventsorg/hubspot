package com.goforboom.hubspot.client.analytics.v2.filters

import com.goforboom.hubspot.client.analytics.v2.values.SortDirection
import com.goforboom.hubspot.client.analytics.v2.values.TimePeriod
import java.time.LocalDate

interface CommonFilter {
    val timePeriod: TimePeriod
    val start: LocalDate
    val end: LocalDate
    val filter: List<String>
    val exclude: List<String>
    val analyticsViewId: String?
    val sortField: String?
    val sortDirection: SortDirection?
    val limit: Int?
    val offset: Int?
}

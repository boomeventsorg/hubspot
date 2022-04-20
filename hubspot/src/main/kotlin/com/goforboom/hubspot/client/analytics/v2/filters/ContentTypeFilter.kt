package com.goforboom.hubspot.client.analytics.v2.filters

import com.goforboom.hubspot.client.analytics.v2.values.ContentType
import com.goforboom.hubspot.client.analytics.v2.values.SortDirection
import com.goforboom.hubspot.client.analytics.v2.values.TimePeriod
import java.time.LocalDate

data class ContentTypeFilter(
    val contentType: ContentType,
    override val timePeriod: TimePeriod,
    override val start: LocalDate,
    override val end: LocalDate,
    override val firstDrilldown: Any?,
    override val secondDrilldown: Any?,
    override val filter: List<String> = listOf(),
    override val exclude: List<String> = listOf(),
    override val analyticsViewId: String?,
    override val sortField: String?,
    override val sortDirection: SortDirection?,
    override val limit: Int?,
    override val offset: Int?,
) : CommonFilter, DrillableFilter

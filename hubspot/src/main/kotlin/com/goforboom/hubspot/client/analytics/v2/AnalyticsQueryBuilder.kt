package com.goforboom.hubspot.client.analytics.v2

import com.goforboom.hubspot.client.analytics.v2.filters.*
import org.apache.http.client.utils.URIBuilder
import java.time.format.DateTimeFormatter

object AnalyticsQueryBuilder {
    private fun build(filter: DrillableFilter): Map<String, String> {
        val uriBuilder = URIBuilder()
        filter.firstDrilldown.let { uriBuilder.addParameter("d1", it.toString()) }
        filter.secondDrilldown.let { uriBuilder.addParameter("d2", it.toString()) }

        return uriBuilder.queryParams.associate { Pair(it.name, it.value) }
    }

    private fun build(filter: CommonFilter): Map<String, String> {
        val uriBuilder = URIBuilder()
        uriBuilder.addParameter("start", filter.start.format(DateTimeFormatter.BASIC_ISO_DATE))
        uriBuilder.addParameter("end", filter.end.format(DateTimeFormatter.BASIC_ISO_DATE))
        filter.filter.forEach { uriBuilder.addParameter("f", it) }
        filter.exclude.forEach { uriBuilder.addParameter("e", it) }
        filter.analyticsViewId.let { uriBuilder.addParameter("filterId", it) }
        filter.sortField.let { uriBuilder.addParameter("sort", it) }
        filter.sortDirection.let { uriBuilder.addParameter("sortDir", it?.direction) }
        filter.limit.let { uriBuilder.addParameter("limit", it.toString()) }
        filter.offset.let { uriBuilder.addParameter("offset", it.toString()) }

        return uriBuilder.queryParams.associate { Pair(it.name, it.value) }
    }

    fun build(filter: BreakdownFilter): Map<String, String> {
        return build(filter as CommonFilter) + build(filter as DrillableFilter)
    }

    fun build(filter: ObjectTypeFilter): Map<String, String> {
        return build(filter as CommonFilter)
    }

    fun build(filter: ContentTypeFilter): Map<String, String> {
        return build(filter as CommonFilter) + build(filter as DrillableFilter)
    }

    fun build(filter: ContentTypePageIdFilter): Map<String, String> {
        return build(filter as CommonFilter) + build(filter as DrillableFilter)
    }

    fun build(filter: ContentTypeBlogIdFilter): Map<String, String> {
        return build(filter as CommonFilter) + build(filter as DrillableFilter)
    }

    fun build(filter: EventsBatchFilter): Map<String, String> {
        val uriBuilder = URIBuilder()
        filter.ids.forEach { uriBuilder.addParameter("id", it) }
        filter.includeDeletes.let { uriBuilder.addParameter("includeDeletes", it.toString()) }

        return uriBuilder.queryParams.associate { Pair(it.name, it.value) }
    }
}

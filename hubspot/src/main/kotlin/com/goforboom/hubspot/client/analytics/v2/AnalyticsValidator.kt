package com.goforboom.hubspot.client.analytics.v2

import com.goforboom.hubspot.client.analytics.v2.filters.CommonFilter
import com.goforboom.hubspot.client.analytics.v2.values.TimePeriod

object AnalyticsValidator {
    fun validate(filter: CommonFilter) {
        val isSimpleTimePeriod = listOf(TimePeriod.DAILY, TimePeriod.MONTHLY, TimePeriod.WEEKLY).contains(filter.timePeriod)
        if(isSimpleTimePeriod && filter.filter.isEmpty()) {
            throw RuntimeException("You must include at least 1 filter when the timePeriod is ${filter.timePeriod}")
        }
        if(isSimpleTimePeriod && filter.limit != null) {
            println("[W]: Limit will be ignored if the timePeriod is ${filter.timePeriod}")
        }
    }
}

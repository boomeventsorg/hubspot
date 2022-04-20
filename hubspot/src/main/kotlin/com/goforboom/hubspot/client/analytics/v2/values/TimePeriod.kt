package com.goforboom.hubspot.client.analytics.v2.values

enum class TimePeriod(val pathVariable: String) {
    TOTAL("total"),
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly"),
    SUMMARIZE_DAILY("summarize/daily"),
    SUMMARIZE_WEEKLY("summarize/weekly"),
    SUMMARIZE_MONTHLY("summarize/monthly"),
    ;
}


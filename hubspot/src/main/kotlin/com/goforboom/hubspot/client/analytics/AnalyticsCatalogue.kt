package com.goforboom.hubspot.client.analytics

import com.goforboom.hubspot.client.analytics.v2.values.Breakdown
import com.goforboom.hubspot.client.analytics.v2.values.ContentType
import com.goforboom.hubspot.client.analytics.v2.values.ObjectType
import com.goforboom.hubspot.client.analytics.v2.values.TimePeriod

object AnalyticsCatalogue {
    object V2 {
        val BREAKDOWN =
            { breakdown: Breakdown, timePeriod: TimePeriod -> "/analytics/v2/reports/${breakdown.pathVariable}/${timePeriod.pathVariable}" }
        val OBJECT_TYPE =
            { objectType: ObjectType, timePeriod: TimePeriod -> "/analytics/v2/reports/${objectType.pathVariable}/${timePeriod.pathVariable}" }
        val CONTENT_TYPE =
            { contentType: ContentType, timePeriod: TimePeriod -> "/analytics/v2/reports/${contentType.pathVariable}/${timePeriod.pathVariable}" }
        val CONTENT_TYPE_AND_PAGE_ID =
            { contentType: ContentType, pageId: String, timePeriod: TimePeriod -> "/analytics/v2/reports/${contentType.pathVariable}/$pageId/sources/${timePeriod.pathVariable}" }
        val CONTENT_TYPE_AND_BLOG_ID =
            { contentType: ContentType, blogId: String, timePeriod: TimePeriod -> "/analytics/v2/reports/${contentType.pathVariable}/content-group/$blogId/sources/${timePeriod.pathVariable}" }
        val OBJECT_TYPE_EXISTS =
            { objectType: ObjectType -> "/analytics/v2/reports/${objectType.pathVariable}/exists" }

        const val EVENTS = "/reports/v2/events"
        const val EVENTS_BATCH = "/reports/v2/events/batch"
        const val VIEWS = "/analytics/v2/views"
    }
}

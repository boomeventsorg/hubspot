package com.goforboom.hubspot.client.analytics.v2

import com.goforboom.hubspot.Client
import com.goforboom.hubspot.client.analytics.AnalyticsCatalogue
import com.goforboom.hubspot.client.analytics.v2.filters.*
import com.goforboom.hubspot.client.analytics.v2.values.ObjectType
import com.goforboom.hubspot.model.http.RequestMethod
import com.goforboom.hubspot.model.http.Requester
import com.goforboom.hubspot.model.mapper.Mapper

class AnalyticsClient(private val hubSpotClient: Client) {
    fun findByBreakdown(filter: BreakdownFilter) {
        AnalyticsValidator.validate(filter)

        val path = AnalyticsCatalogue.V2.BREAKDOWN(filter.breakdown, filter.timePeriod)
        val query = AnalyticsQueryBuilder.build(filter)

        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, path, query, null)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }
    }

    fun findByObjectType(filter: ObjectTypeFilter) {
        AnalyticsValidator.validate(filter)

        val path = AnalyticsCatalogue.V2.OBJECT_TYPE(filter.objectType, filter.timePeriod)
        val query = AnalyticsQueryBuilder.build(filter)

        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, path, query, null)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }
    }

    fun findByContentType(filter: ContentTypeFilter) {
        AnalyticsValidator.validate(filter)

        val path = AnalyticsCatalogue.V2.CONTENT_TYPE(filter.contentType, filter.timePeriod)
        val query = AnalyticsQueryBuilder.build(filter)

        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, path, query, null)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }

    }

    fun findByContentTypeAndPageId(filter: ContentTypePageIdFilter) {
        AnalyticsValidator.validate(filter)

        val path = AnalyticsCatalogue.V2.CONTENT_TYPE_AND_PAGE_ID(filter.contentType, filter.pageId, filter.timePeriod)
        val query = AnalyticsQueryBuilder.build(filter)

        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, path, query, null)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }

    }

    fun findByContentTypeAndBlogId(filter: ContentTypeBlogIdFilter) {
        AnalyticsValidator.validate(filter)

        val path = AnalyticsCatalogue.V2.CONTENT_TYPE_AND_BLOG_ID(filter.contentType, filter.blogId, filter.timePeriod)
        val query = AnalyticsQueryBuilder.build(filter)

        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, path, query, null)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }

    }

    fun findObjectTypeExists(objectType: ObjectType) {
        val path = AnalyticsCatalogue.V2.OBJECT_TYPE_EXISTS(objectType)
        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, path, mapOf(), null)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }

    }

    fun findEvents() {
        val path = AnalyticsCatalogue.V2.EVENTS
        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, path, mapOf(), null)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }

    }

    fun findEventsInBatch(filter: EventsBatchFilter) {
        val path = AnalyticsCatalogue.V2.EVENTS_BATCH
        val query = AnalyticsQueryBuilder.build(filter)

        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, path, query, null)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }
    }

    fun findAnalyticViews() {
        val path = AnalyticsCatalogue.V2.VIEWS
        val response = Requester.requestJson(hubSpotClient, RequestMethod.GET, path, mapOf(), null)

        if (response.isSuccess) {
            return Mapper.mapToObject(response.body)
        } else {
            throw RuntimeException(response.statusText)
        }
    }
}

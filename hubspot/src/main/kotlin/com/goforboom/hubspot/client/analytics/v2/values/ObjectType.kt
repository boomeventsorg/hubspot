package com.goforboom.hubspot.client.analytics.v2.values

enum class ObjectType(val pathVariable: String) {
    EVENT_COMPLETIONS("event-completions"),
    FORMS("forms"),
    PAGES("pages"),
    SOCIAL_ASSISTS("social-assists")
    ;
}

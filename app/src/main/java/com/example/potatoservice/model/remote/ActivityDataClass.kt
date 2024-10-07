package com.example.potatoservice.model.remote

data class Activity(
    val actId: Int,
    val actTitle: String?,
    val actLocation: String?,
    val noticeStartDate: String?,
    val noticeEndDate: String,
    val actStartDate: String?,
    val actEndDate: String,
    val actStartTime: Int,
    val actEndTime: Int,
    val recruitTotalNum: Int,
    val category: String
)

data class ActivityResponse(
    val content: List<Activity>,
    val pageable: Pageable,
    val first: Boolean,
    val last: Boolean,
    val size: Int,
    val number: Int,
    val numberOfElements: Int,
    val empty: Boolean
)

data class Pageable(
    val pageNumber: Int,
    val pageSize: Int,
    val sort: Sort,
    val offset: Int,
    val paged: Boolean,
    val unpaged: Boolean
)

data class Sort(
    val empty: Boolean,
    val sorted: Boolean,
    val unsorted: Boolean
)
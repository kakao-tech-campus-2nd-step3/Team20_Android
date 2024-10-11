package com.example.potatoservice.ui.share

data class Volunteer(
    val id: String,
    val title: String,
    val institution: String,
    val field: String,
    val recruitmentPeriod: String,
    val recruitmentCount: String,
    val activityPeriod: String,
    val volunteerHours: String,
    val address: String,
    val status: String
)

//"actId" id
//"actTitle" 이름
//"actLocation" 활동장소
//"noticeStartDate" 모집기간 (시작)
//"noticeEndDate" 모집기간 (마감)
//"actStartDate" 봉사기간 (시작)
//"actEndDate"  봉사기간 (마감)
//"actStartTime" 봉사시간(시작시간)
//"actEndTime" 봉사시간(마감시간)
//"recruitTotalNum" 모집인원
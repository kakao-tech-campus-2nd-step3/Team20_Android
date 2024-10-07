package com.example.potatoservice.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.potatoservice.ui.share.Volunteer

class HomeViewModel : ViewModel() {

	var title = "봉사 활동 제목"
	var organization = "봉사 기관 이름"
	var category = "봉사 분야"
	var recruitmentPeriod = "모집 기간"
	var recruitmentNumber = "모집 인원"
	var time = "활동 기간"
	var serviceRecognitionTime = "봉사 인정 시간"
	var location = "상세 장소"
	var status = "확정 상태"

	val serviceList:LiveData<List<Volunteer>> = MutableLiveData(
		listOf(
			Volunteer(title, organization, category,
				recruitmentPeriod, recruitmentNumber, time,
				serviceRecognitionTime, location, status
			),
			Volunteer("봉사 활동 제목 12345678912345678911111111끝",
				"조직11111111111111111111111끝", "category111111111111111111끝",
				"recruitmentPeriod",
				"recruitmentNumber11111111111111111111111111끝", "time",
				"serviceRecognitionTime", "location", "확정"
			),
			Volunteer("title", "organization", "category",
				"recruitmentPeriod", "recruitmentNumber", "time",
				"serviceRecognitionTime", "location", "status"
			),
			Volunteer("title", "organization", "category",
				"recruitmentPeriod", "recruitmentNumber", "time",
				"serviceRecognitionTime", "location", "status"
			),
			Volunteer("title", "organization", "category",
				"recruitmentPeriod", "recruitmentNumber", "time",
				"serviceRecognitionTime", "location", "status"
			),
			Volunteer("title", "organization", "category",
				"recruitmentPeriod", "recruitmentNumber", "time",
				"serviceRecognitionTime", "location", "status"
			),
			Volunteer(title, organization, category,
				recruitmentPeriod, recruitmentNumber, time,
				serviceRecognitionTime, location, status
			)
		)
	)
	val searchResultCount = serviceList.value?.count()
	val searchResultCountText = "총 " + searchResultCount.toString() + "건"


	val majorRegoinList = listOf("지역 대분류", "경기", "대구", "서울")
	var minorRegoinList:MutableLiveData<List<List<String>>> = MutableLiveData(listOf(
		listOf("지역 소분류"), listOf("지역 소분류", "남양주", "성남"),
		listOf("지역 소분류","달성구", "동구"), listOf("지역 소분류", "강남구", "종로구"))
	)
	val volunteerList = listOf("봉사 분야", "생활지원 및 주거환경 개선", "교육 및 멘토링", "행정 및 사무지원",
		"문화, 환경 및 국제협력 활동", "보건의료 및 공익활동", "상담 및 자원봉사 교육", "기타 활동")



}
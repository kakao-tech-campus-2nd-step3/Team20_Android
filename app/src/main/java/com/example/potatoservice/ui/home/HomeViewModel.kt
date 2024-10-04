package com.example.potatoservice.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

	var title = "봉사 활동 제목"
	var organization = "봉사 기관 이름"
	var category = "봉사 분야"
	var recruitmentPeriod = "모집 기간"
	var recruitmentNumber = "모집 인원"
	var time = "활동 기간"
	var serviceRecognitionTime = "봉사 인정 시간"
	var location = "상세 장소"

	val serviceList:LiveData<List<Service>> = MutableLiveData(
		listOf(
			Service(title, organization, category,
				recruitmentPeriod, recruitmentNumber, time,
				serviceRecognitionTime, location
			),
			Service("봉사 활동 제목 12345678912345678911111111끝",
				"조직11111111111111111111111끝", "category111111111111111111끝",
				"recruitmentPeriod",
				"recruitmentNumber11111111111111111111111111끝", "time",
				"serviceRecognitionTime", "location"
			),
			Service("title", "organization", "category",
				"recruitmentPeriod", "recruitmentNumber", "time",
				"serviceRecognitionTime", "location"
			),
			Service("title", "organization", "category",
				"recruitmentPeriod", "recruitmentNumber", "time",
				"serviceRecognitionTime", "location"
			),
			Service("title", "organization", "category",
				"recruitmentPeriod", "recruitmentNumber", "time",
				"serviceRecognitionTime", "location"
			),
			Service("title", "organization", "category",
				"recruitmentPeriod", "recruitmentNumber", "time",
				"serviceRecognitionTime", "location"
			),
			Service(title, organization, category,
				recruitmentPeriod, recruitmentNumber, time,
				serviceRecognitionTime, location
			)
		)
	)
}
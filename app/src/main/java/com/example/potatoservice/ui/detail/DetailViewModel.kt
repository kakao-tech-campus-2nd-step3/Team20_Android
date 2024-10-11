package com.example.potatoservice.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.potatoservice.ui.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	application: Application,
	private val repository: HomeRepository
): AndroidViewModel(application){
	//리뷰 퍼센트지 값
	var review1 = "50"
	var review2 = "40"
	var review3 = "30"

	val activityDetail = repository.activityDetail.asLiveData()

	//id에 맞는 봉사 활동 상세 정보를 가져 오는 함수
	fun getDetail(id: Int){
		viewModelScope.launch(Dispatchers.IO) {
			 repository.lookDetail(id)
		}
		Log.d("testt", "viewModelScope ${activityDetail.value?.actTitle}")
	}

	val agePossible: String = getPossibleAge()

	private fun getPossibleAge(): String {
		return if (activityDetail.value != null){
			if (activityDetail.value!!.teenPossible && activityDetail.value!!.adultPossible) {
				"모두 가능"
			} else if (activityDetail.value!!.teenPossible && !activityDetail.value!!.adultPossible) {
				"청소년만 가능"
			} else if (!activityDetail.value!!.teenPossible && activityDetail.value!!.adultPossible) {
				"성인만 가능"
			} else {
				"모두 불가능"
			}
		} else{
			"세부 정보 없음"
		}
	}
	val groupPossible: String = if (activityDetail.value != null){
			if (activityDetail.value!!.groupPossible) "가능" else "불가능"
		} else{
			"세부 정보 없음"
		}

}
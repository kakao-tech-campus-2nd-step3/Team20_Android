package com.example.potatoservice.ui.detail

import android.util.Log
import com.example.potatoservice.model.remote.ActivityDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailRepository @Inject constructor(
	private val detailSearchData: DetailSearchData
){
	//상세 페이지 부분
	private var _activityDetail = MutableStateFlow<ActivityDetail?>(null)
	val activityDetail: StateFlow<ActivityDetail?> = _activityDetail.asStateFlow()
	suspend fun lookDetail(id:Int){
		coroutineScope {
			launch(Dispatchers.IO){
				detailSearchData.lookDetail(id, object : DetailSearchData.DetailCallback {
					override fun onLoaded(activityDetail: ActivityDetail) {
						_activityDetail.value = activityDetail
						Log.d("testt", "onLoaded: ${activityDetail.actTitle}")
					}

					override fun onFailed() {
						_activityDetail.value = ActivityDetail.nullActivityDetail
					}
				})
			}
		}
	}
}
package com.example.potatoservice.ui.home

import android.util.Log
import com.example.potatoservice.model.remote.Activity
import com.example.potatoservice.model.remote.ActivityDetail
import com.example.potatoservice.ui.share.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeRepository @Inject constructor(
	private val remoteDataSource: RemoteDataSource
){
	//홈 페이지 부분
	private val _activityList = MutableStateFlow<List<Activity>>(emptyList())
	val activityList: StateFlow<List<Activity>> = _activityList.asStateFlow()
	fun search(request: Request) {
		remoteDataSource.search(request, object : RemoteDataSource.LoadCallback {
			override fun onLoaded(activities: List<Activity>) {
				_activityList.value = activities
			}

			override fun onFailed() {
				_activityList.value = emptyList()
			}

		})
	}

	//상세 페이지 부분
	private var _activityDetail = MutableStateFlow<ActivityDetail?>(null)
	val activityDetail: StateFlow<ActivityDetail?> = _activityDetail.asStateFlow()
	suspend fun lookDetail(id:Int){
		coroutineScope {
			launch(Dispatchers.IO){
				remoteDataSource.lookDetail(id, object : RemoteDataSource.DetailCallback {
					override fun onLoaded(activityDetail: ActivityDetail) {
						_activityDetail.value = activityDetail
						Log.d("testt", "onLoaded: ${activityDetail.actTitle}")
					}

					override fun onFailed() {
						_activityDetail.value = remoteDataSource.nullActivityDetail
					}
				})
			}
		}
	}
}
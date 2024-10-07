package com.example.potatoservice.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakao.vectormap.LatLng

class MapViewModel : ViewModel() {

    // 현재 위치 저장
    private val _currentLocation = MutableLiveData<LatLng>()
    val currentLocation: LiveData<LatLng> get() = _currentLocation

    // 카메라 위치 저장
    private val _cameraPosition = MutableLiveData<LatLng>()
    val cameraPosition: LiveData<LatLng> get() = _cameraPosition

    // 현재 위치 업데이트 함수
    fun updateCurrentLocation(latLng: LatLng) {
        _currentLocation.value = latLng
    }

    // 카메라 위치 업데이트 함수
    fun updateCameraPosition(latLng: LatLng) {
        _cameraPosition.value = latLng
    }

    data class LocationData(
        val latitude: Double,
        val longitude: Double
    )
}

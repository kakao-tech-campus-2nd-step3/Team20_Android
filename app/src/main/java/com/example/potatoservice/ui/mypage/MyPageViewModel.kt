package com.example.potatoservice.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPageViewModel : ViewModel() {
    private val _volunteerHours = MutableLiveData<Int>()
    val volunteerHours: LiveData<Int> get() = _volunteerHours

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> get() = _progress

    fun setVolunteerHours(hours: Int) {
        _volunteerHours.value = hours
        calculateProgress(hours)
    }

    private fun calculateProgress(hours: Int) {
        val maxHours = 200 // 최대 시간 예시
        val progressValue = (hours * 100) / maxHours
        _progress.value = progressValue
    }
}
package com.example.potatoservice.ui.mypage

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.potatoservice.R

class MyPageViewModel(private val context: Context, private val myPageModel: MyPageModel) : ViewModel() {
    private val _vmVolunteerHours = MutableLiveData<Int>()
    val vmVolunteerHours: LiveData<Int> get() = _vmVolunteerHours

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> get() = _progress

    private val _vmLevel = MutableLiveData<Int>()
    val vmLevel : MutableLiveData<Int> get() = _vmLevel

    private val _vmDialogArray: Array<DialogModel> = myPageModel.dialogArray
    val vmDialogArray: Array<DialogModel> get() = _vmDialogArray

    private val vmSpinnerItems: Array<String> = myPageModel.spinnerItems

    lateinit var vmAdapter: ArrayAdapter<String>

    init {
        vmAdapter = ArrayAdapter(context, R.layout.item_spinner, vmSpinnerItems)
        vmAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        myPageModel.volunteerHousr.observeForever {
            _vmVolunteerHours.value = it
            calculateEx(it)
        }
    }


    private fun calculateEx(hours: Int) {
        //봉사시간 10시간마다 레벨 업
        val level = hours / 10
        val progressValue = hours % 10
        _vmLevel.value = level
        _progress.value = progressValue
    }

    fun setVoulunteerHours(){
        myPageModel.setVolunteerHours()
    }

}

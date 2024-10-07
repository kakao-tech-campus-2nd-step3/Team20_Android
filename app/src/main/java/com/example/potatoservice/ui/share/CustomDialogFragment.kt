package com.example.potatoservice.ui.share

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.potatoservice.databinding.DialogCustomBinding

class CustomDialogFragment : DialogFragment() {

    private lateinit var binding : DialogCustomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogCustomBinding.inflate(inflater,container,false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)


        // 닫기 버튼 이벤트 처리
        binding.customDialogClose.setOnClickListener {
            dismiss()
        }

        // 긍정 버튼 이벤트 처리
        binding.customDialogPositive.setOnClickListener {
            dismiss()
        }

        // 부정 버튼 이벤트 처리
        binding.customDialogNegative.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    // 사이즈 조절
    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
//        dialog?.window?.setLayout(350, 400)
//        dialog?.window?.setLayout(
//            ViewGroup.LayoutParams.WRAP_CONTENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
    }
}

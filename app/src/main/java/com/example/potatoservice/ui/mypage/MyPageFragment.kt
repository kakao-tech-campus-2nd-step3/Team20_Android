package com.example.potatoservice.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.potatoservice.databinding.FragmentMypageBinding
import com.example.potatoservice.ui.share.CustomDialogFragment
import com.example.potatoservice.ui.share.Volunteer

class MyPageFragment : Fragment(), OnVolunteerClickListener{

    private lateinit var binding: FragmentMypageBinding
    private lateinit var myPageViewModel: MyPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // ViewModel과 ViewBinding 초기화
        myPageViewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)
        binding = FragmentMypageBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ProgressBar 설정
        setupProgressBar()

        // RecyclerView 설정
        setupRecyclerView()
    }

    // ProgressBar 설정 함수
    private fun setupProgressBar() {
        // ViewModel의 progress 값을 관찰하고 ProgressBar에 반영
        myPageViewModel.progress.observe(viewLifecycleOwner) { progress ->
            binding.progressBar.progress = progress
        }

        // 초기 봉사 시간을 설정 (예: 190시간)
        myPageViewModel.setVolunteerHours(190)
    }

    // RecyclerView 설정 함수
    private fun setupRecyclerView() {

        // 예시 데이터 리스트 생성
        val volunteers = listOf(
            Volunteer("봉사활동 1", "기관 A", "교육", "2024.09.01 ~ 2024.09.30", "0/5", "2024.10.01 ~ 2024.10.31", "132시간", "서울특별시", "확정 대기 중"),
            Volunteer("봉사활동 2", "기관 B", "환경", "2024.08.01 ~ 2024.08.30", "3/10", "2024.09.01 ~ 2024.09.15", "32시간", "부산광역시", "신청 완료됨"),
            Volunteer("봉사활동 3", "기관 B", "환경", "2024.08.01 ~ 2024.08.30", "3/10", "2024.09.01 ~ 2024.09.15", "32시간", "부산광역시", "신청 완료됨"),
            Volunteer("봉사활동 4", "기관 B", "환경", "2024.08.01 ~ 2024.08.30", "3/10", "2024.09.01 ~ 2024.09.15", "32시간", "부산광역시", "신청 완료됨")
            // 더 많은 데이터 추가 가능
        )


        // 어댑터 설정
        val adapter = VolunteerAdapter(volunteers,this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onVolunteerClick(volunteer: Volunteer) {
        //todo 커스텀뷰 만들기
//        Toast.makeText(context, "${volunteer.title}", Toast.LENGTH_SHORT).show()

        val customDialog = CustomDialogFragment()
        customDialog.show(parentFragmentManager,"customDialog")

    }
}

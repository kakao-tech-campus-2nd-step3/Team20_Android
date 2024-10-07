package com.example.potatoservice.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.potatoservice.databinding.ItemVolunteerBinding
import com.example.potatoservice.ui.share.Volunteer

class VolunteerAdapter(private val volunteerList: List<Volunteer>, private val listener: OnVolunteerClickListener) :
    RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder>() {

    inner class VolunteerViewHolder(val binding: ItemVolunteerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(volunteer: Volunteer) {
            // ViewBinding을 통해 뷰에 접근하여 데이터 바인딩
            binding.tvVolunteerTitle.text = volunteer.title
            binding.tvInstitutionName.text = volunteer.institution
            binding.tvVolunteerField.text = volunteer.field
            binding.tvRecruitmentPeriod.text = volunteer.recruitmentPeriod
            binding.tvRecruitmentCount.text = volunteer.recruitmentCount
            binding.tvActivityPeriod.text = volunteer.activityPeriod
            binding.tvVolunteerHours.text = volunteer.volunteerHours
            binding.tvVolunteerAddress.text = volunteer.address
            binding.tvVolunteerStatus.text = volunteer.status
            binding.btnAction.setOnClickListener {
                listener.onVolunteerClick(volunteer)
            }
        }
    }

    // 레이아웃 인플레이트
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolunteerViewHolder {
        val binding = ItemVolunteerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VolunteerViewHolder(binding)
    }

    // 데이터와 뷰 연결
    override fun onBindViewHolder(holder: VolunteerViewHolder, position: Int) {
        holder.bind(volunteerList[position])
    }

    // 아이템 수를 반환
    override fun getItemCount(): Int {
        return volunteerList.size
    }
}

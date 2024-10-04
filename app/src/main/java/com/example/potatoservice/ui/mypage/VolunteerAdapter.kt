package com.example.potatoservice.ui.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.potatoservice.R

class VolunteerAdapter(private val volunteerList: List<Volunteer>) :
    RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder>() {

    inner class VolunteerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tvVolunteerTitle)
        val institution = itemView.findViewById<TextView>(R.id.tvInstitutionName)
        val field = itemView.findViewById<TextView>(R.id.tvVolunteerField)
        val recruitmentPeriod = itemView.findViewById<TextView>(R.id.tvRecruitmentPeriod)
        val recruitmentCount = itemView.findViewById<TextView>(R.id.tvRecruitmentCount)
        val activityPeriod = itemView.findViewById<TextView>(R.id.tvActivityPeriod)
        val volunteerHours = itemView.findViewById<TextView>(R.id.tvVolunteerHours)
        val address = itemView.findViewById<TextView>(R.id.tvVolunteerAddress)
        val status = itemView.findViewById<TextView>(R.id.tvVolunteerStatus)
        val actionButton = itemView.findViewById<Button>(R.id.btnAction)

        fun bind(volunteer: Volunteer) {
            title.text = volunteer.title
            institution.text = volunteer.institution
            field.text = volunteer.field
            recruitmentPeriod.text = volunteer.recruitmentPeriod
            recruitmentCount.text = volunteer.recruitmentCount
            activityPeriod.text = volunteer.activityPeriod
            volunteerHours.text = volunteer.volunteerHours
            address.text = volunteer.address
            status.text = volunteer.status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolunteerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_volunteer, parent, false)
        return VolunteerViewHolder(view)
    }

    override fun onBindViewHolder(holder: VolunteerViewHolder, position: Int) {
        holder.bind(volunteerList[position])
    }

    override fun getItemCount(): Int {
        return volunteerList.size
    }
}
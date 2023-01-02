package com.leventgundogdu.studentteacherappointmentsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeacherAdapter(private val appointmentsList : ArrayList<AppointmentsList>):
    RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.appointment_list,parent,false)
        return TeacherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {

        val currentItem = appointmentsList [position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.studentText.text = currentItem.studentName
        holder.studentText2.text = currentItem.appointmentDate
        holder.studentText3.text = currentItem.appointmentTime
    }

    override fun getItemCount(): Int {
        return appointmentsList.size
    }


    class TeacherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleImage : ImageView = itemView.findViewById(R.id.title_image)
        val studentText : TextView = itemView.findViewById(R.id.student_Text)
        val studentText2 : TextView = itemView.findViewById(R.id.student_Text2)
        val studentText3 : TextView = itemView.findViewById(R.id.student_Text3)

    }

}
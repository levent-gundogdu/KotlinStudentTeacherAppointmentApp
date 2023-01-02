package com.leventgundogdu.studentteacherappointmentsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppointmentAdaptor(private val appointmentList:ArrayList<Appointment>) : RecyclerView.Adapter<AppointmentAdaptor.AppointmentViewHolder>() {

    class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val textView1 : TextView = itemView.findViewById(R.id.txtstudentName)
        val textView2 : TextView = itemView.findViewById(R.id.txtappointmentDate)
        val textView3 : TextView = itemView.findViewById(R.id.txtappointmentTime)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.appointment_item , parent , false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointmentList[position]
        holder.imageView.setImageResource(appointment.image)
        holder.textView1.text = appointment.name1
        holder.textView2.text = appointment.name2
        holder.textView3.text = appointment.name3
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }

}
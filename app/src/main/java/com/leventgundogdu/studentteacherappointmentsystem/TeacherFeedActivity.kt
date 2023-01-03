package com.leventgundogdu.studentteacherappointmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.leventgundogdu.studentteacherappointmentsystem.databinding.ActivityTeacherFeedBinding

class TeacherFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherFeedBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    //private lateinit var appointmentList : ArrayList<Appointment>
    private lateinit var adapter: AppointmentAdaptor
    private lateinit var appointmentAdaptor: AppointmentAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        firestore = Firebase.firestore

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = AppointmentAdaptor(mutableListOf())
        recyclerView.adapter = adapter

        getData() { studentName, date, time ->

            val appointment = Appointment(R.drawable.user1, studentName, date, time)
            adapter.addAppointment(appointment)
            adapter.notifyDataSetChanged()

//            val studentName = studentName
//            val date = date
//            val time = time
//            println(studentName)
//            println(date)
//            println(time)
//            appointmentList.add(Appointment(R.drawable.user1, studentName, date, time))
//            appointmentList.add(Appointment(R.drawable.user1, studentName, date, time))
//            appointmentList.add(Appointment(R.drawable.user1, studentName, date, time))
//            appointmentList.add(Appointment(R.drawable.user1, studentName, date, time))
//            appointmentList.add(Appointment(R.drawable.user1, studentName, date, time))


        }



    }

    private fun getData(callback: (studentName: String, date: String, time: String) -> Unit) {
        val appointmentData = firestore.collection("Appointments")
        val userData = firestore.collection("Users")

        appointmentData.get().addOnSuccessListener {
            for (document in it) {
                if (document.getString("teacherEmail").equals(auth.currentUser!!.email)) {
                    val date = document.getString("date")!!
                    val time = document.getString("time")!!

                    userData.get().addOnSuccessListener {
                        for (studentData in it) {
                            if (document.id == studentData.id) {
                                val studentName = studentData.getString("fullName")!!
                                callback(studentName, date, time)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.teacher_feed_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.sign_out) {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

}
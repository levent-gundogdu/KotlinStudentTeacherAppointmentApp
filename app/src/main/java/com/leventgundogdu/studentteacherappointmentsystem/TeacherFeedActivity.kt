package com.leventgundogdu.studentteacherappointmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.leventgundogdu.studentteacherappointmentsystem.databinding.ActivityTeacherFeedBinding

class TeacherFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherFeedBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var appointmentList : ArrayList<Appointment>
    private lateinit var appointmentAdaptor: AppointmentAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        appointmentList = ArrayList()

        appointmentList.add(Appointment(R.drawable.user1, "Öğrenci Adı Çekilecek", "Randevu Günü Çekilecek", "Randevu Zamanı Çekilecek"))
        appointmentList.add(Appointment(R.drawable.user1, "Öğrenci Adı Çekilecek", "Randevu Günü Çekilecek", "Randevu Zamanı Çekilecek"))
        appointmentList.add(Appointment(R.drawable.user1, "Öğrenci Adı Çekilecek", "Randevu Günü Çekilecek", "Randevu Zamanı Çekilecek"))
        appointmentList.add(Appointment(R.drawable.user1, "Öğrenci Adı Çekilecek", "Randevu Günü Çekilecek", "Randevu Zamanı Çekilecek"))
        appointmentList.add(Appointment(R.drawable.user1, "Öğrenci Adı Çekilecek", "Randevu Günü Çekilecek", "Randevu Zamanı Çekilecek"))

        appointmentAdaptor = AppointmentAdaptor(appointmentList)
        recyclerView.adapter = appointmentAdaptor

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.feed_menu, menu)

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
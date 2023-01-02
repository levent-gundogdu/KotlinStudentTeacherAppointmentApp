package com.leventgundogdu.studentteacherappointmentsystem

import android.content.Intent
import android.content.LocusId
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.leventgundogdu.studentteacherappointmentsystem.databinding.ActivityTeacherFeedBinding
import java.util.Date

class TeacherFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherFeedBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<AppointmentsList>
    lateinit var imageId: Array<Int>
    lateinit var studentName: Array<String>
    lateinit var appointmentDate: Array <String>
    lateinit var appointmentTime: Array <String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        imageId = arrayOf(R.drawable.user1)

        studentName = arrayOf("STUDENT NAME BURA")
        appointmentDate= arrayOf("DATE BURA")
        appointmentTime= arrayOf("TIME BURA")


        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<AppointmentsList>()


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
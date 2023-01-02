package com.leventgundogdu.studentteacherappointmentsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class DetailedActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val course = intent.getParcelableExtra<Course>("course")
        if (course != null) {
            val textView : TextView = findViewById(R.id.detailedActivityTv)
            val imageView: ImageView = findViewById(R.id.detailedActivityIv)



            textView.text = course.name
            imageView.setImageResource(course.image)

            fun createChatClicked(view: View) {

                

            }


        }
    }
}
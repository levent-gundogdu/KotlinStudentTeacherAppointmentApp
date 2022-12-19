package com.leventgundogdu.studentteacherappointmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.core.Tag
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.leventgundogdu.studentteacherappointmentsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        firestore = Firebase.firestore

        val currentUser = auth.currentUser

        if (currentUser != null) {
            checkUserAccessLevel(currentUser.uid)
        }



    }

    fun signInClicked(view: View) {

        val email = binding.emailText.text.toString()
        val password = binding.passwordText.text.toString()

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Enter email and password!", Toast.LENGTH_LONG).show()
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                checkUserAccessLevel(auth.currentUser!!.uid)

            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkUserAccessLevel(uid: String) {
        val uniqueId = firestore.collection("Users").document(uid)
        //extract the data from document

        uniqueId.get().addOnSuccessListener {
            Log.d("TAG", "onSuccess: ${it.data}")

            //identify if the user has access level
            if (it.getString("isAdmin") != null) {
                //user is admin
                val intent = Intent(this, TeacherFeedActivity::class.java)
                startActivity(intent)
                finish()
            }

            if (it.getString("isUser") != null) {
                //user is not admin
                val intent = Intent(this, AppointmentFeedActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun signUpClicked(view: View) {

        val intent = Intent(this@MainActivity, SignUpActivity::class.java)
        startActivity(intent)

    }



}
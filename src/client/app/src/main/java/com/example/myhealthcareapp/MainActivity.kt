package com.example.myhealthcareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myhealthcareapp.login.LoginFragment
import com.example.myhealthcareapp.makeAppointment.MakeAppointmentFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        firestore = Firebase.firestore

        bottomNavigation.visibility = View.GONE

        if(mAuth.currentUser == null){
            replaceFragment(LoginFragment(), R.id.fragment_container)
        }
        else{
            replaceFragment(MakeAppointmentFragment(), R.id.fragment_container)
        }
    }

    fun replaceFragment(fragment: Fragment, containerId: Int, addToBackStack:Boolean = false, withAnimation:Boolean = false){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        when(addToBackStack){
            true -> {
                transaction.addToBackStack(null)
            }
        }
        transaction.commit()
    }

    private fun initBottomNavigation(){
        bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.make_appointment -> {
                    //TODO: implement it
                    true
                }
                R.id.feedback -> {
                    //TODO: implement it
                    true
                }
                R.id.my_appointments -> {
                    //TODO: implement it
                    true
                }
                else -> false
            }
        }
    }
}
package com.example.myhealthcareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.myhealthcareapp.fragments.login.LoginFragment
import com.example.myhealthcareapp.fragments.makeAppointment.HospitalListFragment
import com.example.myhealthcareapp.fragments.profile.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var fireStore: FirebaseFirestore
    lateinit var searchView : SearchView
    lateinit var searchIcon: MenuItem
    lateinit var profileIconIcon: MenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        fireStore = Firebase.firestore
        bottomNavigation.visibility = View.GONE
        searchView = findViewById(R.id.search_button)
        searchIcon = topAppBar.menu.findItem(R.id.search)
        profileIconIcon = topAppBar.menu.findItem(R.id.profile)

        initBottomNavigation()
        initTopBar()

        if(mAuth.currentUser == null){
            topAppBar.visibility = View.GONE
            replaceFragment(LoginFragment(), R.id.fragment_container)
        }
        else{
            replaceFragment(HospitalListFragment(), R.id.fragment_container)
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
                    val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
                    if(fragment !is HospitalListFragment)
                        replaceFragment(HospitalListFragment(), R.id.fragment_container)
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

    private fun initTopBar(){
        profileIconIcon.setOnMenuItemClickListener {
            replaceFragment(ProfileFragment(), R.id.fragment_container)
            true
        }
    }
}
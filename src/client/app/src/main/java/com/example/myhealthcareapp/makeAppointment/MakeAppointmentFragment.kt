package com.example.myhealthcareapp.makeAppointment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.HospitalRecyclerViewAdapter
import com.example.myhealthcareapp.cache.Cache
import com.example.myhealthcareapp.login.LoginFragment
import com.example.myhealthcareapp.models.Hospital
import com.example.myhealthcareapp.models.user.Client
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_make_appointment.*

class MakeAppointmentFragment : Fragment() {
    private lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_make_appointment, container, false)

        val email = (activity as MainActivity).mAuth.currentUser!!.email!!
        (activity as MainActivity).fireStore.collection("users").document(email).get()
            .addOnSuccessListener { document ->
                if(document != null){
                    val userId = (activity as MainActivity).mAuth.currentUser!!.uid
                    val firstName = document.data!!["firstName"].toString()
                    val lastName = document.data!!["lastName"].toString()
                    val personalCode = document.data!!["personalCode"].toString()

                    val client = Client(userId, firstName, lastName, email, personalCode)
                    Cache.setClient(client)
                    Log.d(ContentValues.TAG, client.toString())
                    setupUI(view)
                }
                else{
                    Log.d(ContentValues.TAG, "No such user")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Get failed with ", exception)
            }

        return view
    }

    private fun setupUI(view: View){
        (activity as MainActivity).bottomNavigation.visibility = View.VISIBLE

//        button = view.findViewById(R.id.button)
//        button.setOnClickListener {
//            (activity as MainActivity).mAuth.signOut()
//            (activity as MainActivity).topAppBar.visibility = View.GONE
//            (activity as MainActivity).replaceFragment(LoginFragment(), R.id.fragment_container)
//            (activity as MainActivity).bottomNavigation.visibility = View.GONE
//        }

        //recycler view stuff
        val exampleList = generateDummyList(20)

        val recycler_view = view.findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.adapter = HospitalRecyclerViewAdapter(exampleList)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): MutableList<Hospital> {

        val list : MutableList<Hospital> = mutableListOf()
        for (i in 0 until size) {

            val item = Hospital(i,"Hospital$i","0123456789","asdf","Kukutyinfalva","asdf","asdf")
            list += item
        }

        return list
    }
}
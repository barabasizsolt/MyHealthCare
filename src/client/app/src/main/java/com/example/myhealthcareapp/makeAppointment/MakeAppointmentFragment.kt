package com.example.myhealthcareapp.makeAppointment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.HospitalRecyclerViewAdapter
import com.example.myhealthcareapp.cache.Cache
import com.example.myhealthcareapp.models.Hospital
import com.example.myhealthcareapp.models.user.Client
import kotlinx.android.synthetic.main.activity_main.*

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
        (activity as MainActivity).topAppBar.menu[0].isVisible = true
        (activity as MainActivity).topAppBar.menu[1].isVisible = true
        (activity as MainActivity).topAppBar.menu[2].isVisible = true

        val exampleList = generateDummyList(20)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.adapter = HospitalRecyclerViewAdapter(exampleList)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): MutableList<Hospital> {

        val list : MutableList<Hospital> = mutableListOf()
        for (i in 0 until size) {
            val item = Hospital(i,"Policlinica 2",
                "0744077777",
                "asdf",
                "Bulevardul 1 Decembrie 1918, Târgu Mureș 540011",
                "asdf",
                "asdf"
            )
            list += item
        }

        return list
    }
}
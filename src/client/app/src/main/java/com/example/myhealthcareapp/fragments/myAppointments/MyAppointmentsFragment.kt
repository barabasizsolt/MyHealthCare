package com.example.myhealthcareapp.fragments.myAppointments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import android.content.ContentValues
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.adapters.HospitalRecyclerViewAdapter
import com.example.myhealthcareapp.adapters.MyAppointmentsAdapter
import com.example.myhealthcareapp.cache.Cache
import com.example.myhealthcareapp.models.user.Client
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.Appointment
import com.example.myhealthcareapp.models.Hospital
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MyAppointmentsFragment : BaseFragment(), OnItemClickListener {
    private lateinit var exampleList: MutableList<Appointment>
    lateinit var adapter : MyAppointmentsAdapter

    lateinit var appointmentDate : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_appointments, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = (mActivity as MainActivity).mAuth.currentUser!!.email!!
        (mActivity as MainActivity).fireStore.collection("users").document(email).get()
            .addOnSuccessListener { document ->
                if(document != null){
                    val userId = (mActivity as MainActivity).mAuth.currentUser!!.uid
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
    }

    private fun setupUI(view: View){
        //appointmentDate = view.findViewById(R.id.appointment_date)

        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.my_appointments)
        (mActivity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (mActivity as MainActivity).topAppBar.menu.findItem(R.id.search).isVisible = true
        (mActivity as MainActivity).topAppBar.menu.findItem(R.id.profile).isVisible = true

        exampleList = generateDummyList(20)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = MyAppointmentsAdapter(exampleList, this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): MutableList<Appointment> {

        val list : MutableList<Appointment> = mutableListOf()
        for (i in 0 until size) {
            val item = Appointment(i,"asfd",
                1,
                1,
                1,
                "2021-12-21 16:30",
                "2021-12-21 17:00",
                "ok"
            )
            list += item
        }

        return list
    }

    override fun onItemClick(position: Int) {

        val summary = arrayOf(
            "Hospital: " + "Policlinica 2", //Hospital name,
            "Department: " + "Neurology", //Department name,
            "Medic: " + "Dr House1", //Medic name
            //"Date & Time: " + appointmentDate.text + "-" + appointmentDate.text.toString().takeLast(5), //Appointment date & time
            "Date & Time: " + "2021-12-21 16:30" + "-" + "17:00", //Appointment date & time
        )

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.summary))
            .setItems(summary) {_, _ ->}
            .setNeutralButton(resources.getString(R.string.cancel)) { _, _ -> }
            .setNegativeButton(resources.getString(R.string.decline)) { _, _ ->
                Toast.makeText(requireContext(), "Appointment was declined", Toast.LENGTH_SHORT).show()
                //TODO navigate back
            }
            .show()
    }
}
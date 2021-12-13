package com.example.myhealthcareapp.fragments.myAppointments

import android.os.Bundle
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
import com.example.myhealthcareapp.adapters.MyAppointmentsAdapter
import com.example.myhealthcareapp.cache.Cache
import com.example.myhealthcareapp.models.user.Client
import kotlinx.android.synthetic.main.activity_main.*
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.Appointment
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MyAppointmentsFragment : BaseFragment(), OnItemClickListener {
    private lateinit var appointments: MutableList<Appointment>
    private lateinit var adapter : MyAppointmentsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_appointments, container, false)

        appointments = generateDummyList(20)
        recyclerView = view.findViewById(R.id.recycler_view)
        adapter = MyAppointmentsAdapter(appointments, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.my_appointments)
        (mActivity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (mActivity as MainActivity).searchIcon.isVisible = true
        (mActivity as MainActivity).profileIcon.isVisible = true

        return view
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
            .show()
    }
}
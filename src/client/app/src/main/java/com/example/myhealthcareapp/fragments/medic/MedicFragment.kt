package com.example.myhealthcareapp.fragments.medic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.MedicAppointmentAdapter
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.models.Appointment
import kotlinx.android.synthetic.main.activity_main.*

class MedicFragment : BaseFragment(){
    private lateinit var appointments: MutableList<Appointment>
    private lateinit var adapter: MedicAppointmentAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_medic, container, false)

        appointments = generateDummyList(20)
        recyclerView = view.findViewById(R.id.recycler_view)
        adapter = MedicAppointmentAdapter(appointments)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.upcoming_appointments)
        (mActivity as MainActivity).bottomNavigation.visibility = View.GONE
        (mActivity as MainActivity).topAppBar.visibility = View.VISIBLE
        (mActivity as MainActivity).searchIcon.isVisible = false
        (mActivity as MainActivity).profileIcon.isVisible = true

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (mActivity as MainActivity).profileIcon.setOnMenuItemClickListener {
            (mActivity as MainActivity).replaceFragment(MedicProfileFragment(), R.id.fragment_container, addToBackStack = true)
            true
        }
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
}
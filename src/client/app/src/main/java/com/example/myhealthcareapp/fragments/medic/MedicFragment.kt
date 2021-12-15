package com.example.myhealthcareapp.fragments.medic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.MedicAppointmentAdapter
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.models.response.ClientAppointmentResponse

class MedicFragment : BaseFragment(){
    private lateinit var appointments: MutableList<ClientAppointmentResponse>
    private lateinit var adapter: MedicAppointmentAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_medic, container, false)

//        appointments = generateDummyList(20)
//        recyclerView = view.findViewById(R.id.recycler_view)
//        adapter = MedicAppointmentAdapter(appointments)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.setHasFixedSize(true)
//
//        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.upcoming_appointments)
//        (mActivity as MainActivity).bottomNavigation.visibility = View.GONE
//        (mActivity as MainActivity).topAppBar.visibility = View.VISIBLE
//        (mActivity as MainActivity).searchIcon.isVisible = false
//        (mActivity as MainActivity).profileIcon.isVisible = true

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (mActivity as MainActivity).profileIcon.setOnMenuItemClickListener {
            (mActivity as MainActivity).replaceFragment(MedicProfileFragment(), R.id.fragment_container, addToBackStack = true)
            true
        }
    }
}
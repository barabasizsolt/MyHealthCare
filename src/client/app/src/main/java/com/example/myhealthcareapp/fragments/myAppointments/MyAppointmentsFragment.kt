package com.example.myhealthcareapp.fragments.myAppointments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.adapters.MyAppointmentsAdapter
import com.example.myhealthcareapp.api.MyHealthCareViewModel
import kotlinx.android.synthetic.main.activity_main.*
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.response.Appointment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyAppointmentsFragment : BaseFragment(), OnItemClickListener {
    private lateinit var appointments: MutableList<Appointment>
    private lateinit var singleAppointment: Appointment
    private lateinit var adapter : MyAppointmentsAdapter
    private val viewModel by viewModel<MyHealthCareViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_appointments, container, false)

        //TODO get the user id
        viewModel.getAppointments(15)
        viewModel.myAppointments.observe(viewLifecycleOwner, { response ->
            if(response.isSuccessful){
                Log.d("myAppointments", response.body().toString())
                appointments = response.body()?.data as MutableList
                setupUI(view)
            }
            else {
                Log.e("myAppointments", response.errorBody().toString())
            }
        })


        return view
    }

    private fun setupUI(view : View){
        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.my_appointments)
        (mActivity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (mActivity as MainActivity).searchIcon.isVisible = false
        (mActivity as MainActivity).profileIcon.isVisible = true

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = MyAppointmentsAdapter(appointments, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        (mActivity as MainActivity).setProfileIconListener()

    }

    override fun onItemClick(position: Int) {
        viewModel.getSingleAppointment(appointments[position].appointmentId)

        viewModel.singleAppointment.observe(viewLifecycleOwner, { response ->
            if(response.isSuccessful){
                Log.d("singleAppointment", response.body().toString())
                singleAppointment = response.body()?.data!!

                val summary = arrayOf(
                    "Hospital: " + singleAppointment.hospitalName, //Hospital name,
                    "Department: " + singleAppointment.medicalDepartmentName, //Department name,
                    "Medic: " + singleAppointment.medicName, //Medic name
                    "Date & Time: " + singleAppointment.scheduleStartDate + ", " + singleAppointment.scheduleEndDate, //Appointment date & time
                )

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.summary))
                    .setItems(summary) {_, _ ->}
                    .show()
            }
            else {
                Log.e("singleAppointment", response.errorBody().toString())
            }
        })
    }

}
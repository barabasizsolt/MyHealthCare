package com.example.myhealthcareapp.makeAppointment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.BookAppointmentAdapter
import com.example.myhealthcareapp.constants.Constant.getDate
import com.example.myhealthcareapp.models.Medic
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BookAppointmentFragment : Fragment() {
    private lateinit var recyclerview: RecyclerView
    private lateinit var dateButton: FloatingActionButton
    private lateinit var timeButton: FloatingActionButton
    private lateinit var bookAppointment: Button
    private lateinit var appointmentDate: TextView
    private lateinit var appointmentTime: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_appointment, container, false)

        setupUI(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateButton.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()
            datePicker.show(childFragmentManager, "tag")
            datePicker.addOnPositiveButtonClickListener {
                val date = getDate(it)
                appointmentDate.text = date
            }
        }
        timeButton.setOnClickListener {
        }
        bookAppointment.setOnClickListener {

        }
    }

    private fun setupUI(view: View){
        recyclerview = view.findViewById(R.id.medic_recyclerview)
        recyclerview.adapter = BookAppointmentAdapter(generateDummyList())
        recyclerview.setHasFixedSize(true)
        dateButton = view.findViewById(R.id.appointment_date_btn)
        timeButton = view.findViewById(R.id.appointment_time_btn)
        bookAppointment = view.findViewById(R.id.make_appointment_btn)
        appointmentDate = view.findViewById(R.id.appointment_date)
        appointmentTime = view.findViewById(R.id.appointment_time)
    }

    private fun generateDummyList(): MutableList<Medic> {
        val list : MutableList<Medic> = mutableListOf()
        for (i in 0 until 20) {
            val item = Medic(i, "Dr House")
            list += item
        }
        return list
    }
}
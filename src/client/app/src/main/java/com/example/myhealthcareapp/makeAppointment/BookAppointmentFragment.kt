package com.example.myhealthcareapp.makeAppointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.BookAppointmentAdapter
import com.example.myhealthcareapp.models.Medic

class BookAppointmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_appointment, container, false)

        setupUI(view)

        return view
    }

    private fun setupUI(view: View){
        val recyclerview = view.findViewById<RecyclerView>(R.id.medic_recyclerview)
        recyclerview.adapter = BookAppointmentAdapter(generateDummyList())
        recyclerview.setHasFixedSize(true)
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
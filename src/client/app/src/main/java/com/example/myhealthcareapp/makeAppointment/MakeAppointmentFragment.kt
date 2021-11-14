package com.example.myhealthcareapp.makeAppointment

import android.os.Bundle
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
import com.example.myhealthcareapp.login.LoginFragment
import com.example.myhealthcareapp.models.Hospital
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_make_appointment.*

class MakeAppointmentFragment : Fragment() {
    private lateinit var button: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_make_appointment, container, false)

        val exampleList = generateDummyList(20)

        val recycler_view = view.findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.adapter = HospitalRecyclerViewAdapter(exampleList)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.setHasFixedSize(true)

        return view
    }

    private fun generateDummyList(size: Int): MutableList<Hospital> {

        val list : MutableList<Hospital> = mutableListOf()
        for (i in 0 until size) {

            val item = Hospital(i,"Hospital$i","0123456789","asdf","asdf","asdf","asdf")
            list += item
        }

        return list
    }
}
package com.example.myhealthcareapp.makeAppointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.constants.Constant.HospitalId


class DepartmentListFragment() : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_department_list, container, false)

        val text_view = view.findViewById<TextView>(R.id.text_view)

        text_view.text = arguments?.getString(HospitalId)
        return view
    }


}
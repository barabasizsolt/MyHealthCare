package com.example.myhealthcareapp.makeAppointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.MedicalDepartmentRecyclerViewAdapter
import com.example.myhealthcareapp.constants.Constant.HospitalId
import com.example.myhealthcareapp.models.MedicalDepartment
import kotlinx.android.synthetic.main.activity_main.*


class MedicalDepartmentListFragment() : Fragment() {

    private lateinit var exampleList: MutableList<MedicalDepartment>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_medical_department_list, container, false)

        val id = arguments?.getInt(HospitalId)

        setupUI(view,id)

        return view
    }

    private fun setupUI(view: View, id: Int?){
        (activity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (activity as MainActivity).topAppBar.menu.findItem(R.id.search).isVisible = true
        (activity as MainActivity).topAppBar.menu.findItem(R.id.profile).isVisible = true

        exampleList = generateDummyList(20, id)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.adapter = MedicalDepartmentRecyclerViewAdapter(exampleList)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int, id: Int?): MutableList<MedicalDepartment> {

        val list : MutableList<MedicalDepartment> = mutableListOf()
        for (i in 0 until size) {
            val item = MedicalDepartment(i,"Neurology_$id" + "_$i",
                "0744077777",
                "Floor 3, room 314",
                "asdf",
                id
            )
            list += item
        }

        return list
    }

}
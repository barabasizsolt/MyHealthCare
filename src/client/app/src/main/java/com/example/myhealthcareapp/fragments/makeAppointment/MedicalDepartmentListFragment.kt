package com.example.myhealthcareapp.fragments.makeAppointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.MedicalDepartmentRecyclerViewAdapter
import com.example.myhealthcareapp.constants.Constant.HospitalId
import com.example.myhealthcareapp.constants.Constant.HospitalName
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.Hospital
import com.example.myhealthcareapp.models.MedicalDepartment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MedicalDepartmentListFragment() : BaseFragment(), OnItemClickListener {
    private lateinit var exampleList: MutableList<MedicalDepartment>
    private lateinit var medicalDepartmentAdapter: MedicalDepartmentRecyclerViewAdapter
    private lateinit var selectedHospital: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_medical_department_list, container, false)

        val id = arguments?.getInt(HospitalId)

        setupUI(view,id)

        return view
    }

    private fun setupUI(view: View, id: Int?){
        (mActivity as MainActivity).searchView.queryHint = (mActivity).getString(R.string.department_hint)
        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.select_department)
        (mActivity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (mActivity as MainActivity).searchIcon.isVisible = true
        (mActivity as MainActivity).profileIconIcon.isVisible = true

        selectedHospital = view.findViewById(R.id.selected_hospital_name)
        selectedHospital.text = arguments?.getString(HospitalName)

        exampleList = generateDummyList(20, id)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view)
        medicalDepartmentAdapter = MedicalDepartmentRecyclerViewAdapter(exampleList, this)
        recyclerview.adapter = medicalDepartmentAdapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setHasFixedSize(true)

        (mActivity as MainActivity).searchIcon.setOnMenuItemClickListener{
            (mActivity as MainActivity).searchView.visibility = View.VISIBLE
            (mActivity as MainActivity).topAppBar.visibility = View.GONE
            (mActivity as MainActivity).searchView.isIconified = false

            return@setOnMenuItemClickListener true
        }

        (mActivity as MainActivity).searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(query: String?): Boolean {
                filter(query)
                return true
            }
        })

        (mActivity as MainActivity).searchView.setOnCloseListener {
            (mActivity as MainActivity).searchView.visibility = View.GONE
            (mActivity as MainActivity).topAppBar.visibility = View.VISIBLE
            true
        }
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

    override fun onItemClick(position: Int) {
        (mActivity as MainActivity).replaceFragment(BookAppointmentFragment(), R.id.fragment_container, true)
    }

    private fun filter(text: String?) {
        val filteredList: MutableList<MedicalDepartment> = mutableListOf()

        for (item in exampleList) {
            // checking if the entered string matched with any item of our recycler view.
            if (text != null) {
                if (item.medicalDepartmentName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                    filteredList.add(item)
                }
            }
        }
        if (filteredList.isEmpty())
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()

        medicalDepartmentAdapter.filterList(filteredList)
    }
}
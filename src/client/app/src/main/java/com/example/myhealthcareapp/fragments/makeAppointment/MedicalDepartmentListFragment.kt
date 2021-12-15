package com.example.myhealthcareapp.fragments.makeAppointment

import android.os.Bundle
import android.util.Log
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
import com.example.myhealthcareapp.api.MyHealthCareViewModel
import com.example.myhealthcareapp.constants.Constant.HospitalId
import com.example.myhealthcareapp.constants.Constant.HospitalName
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.response.MedicalDepartment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class MedicalDepartmentListFragment : BaseFragment(), OnItemClickListener {
    private lateinit var departments: MutableList<MedicalDepartment>
    private lateinit var medicalDepartmentAdapter: MedicalDepartmentRecyclerViewAdapter
    private lateinit var selectedHospital: TextView
    private lateinit var recyclerView: RecyclerView
    private val viewModel by sharedViewModel<MyHealthCareViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_medical_department_list, container, false)

        selectedHospital = view.findViewById(R.id.selected_hospital_name)
        recyclerView = view.findViewById(R.id.recycler_view)

        selectedHospital.text = arguments?.getString(HospitalName)
        arguments?.getInt(HospitalId)?.let { viewModel.loadDepartments(it) }
        viewModel.departments.observe(viewLifecycleOwner, { response ->
            if(response.isSuccessful) {
                departments = response.body()?.data as MutableList
                Log.d("Departments", departments.toString())
                setupUI()
            }
        })

        return view
    }

    private fun setupUI(){
        (mActivity as MainActivity).searchView.queryHint = (mActivity).getString(R.string.department_hint)
        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.select_department)
        (mActivity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (mActivity as MainActivity).searchIcon.isVisible = true
        (mActivity as MainActivity).profileIcon.isVisible = true

        medicalDepartmentAdapter = MedicalDepartmentRecyclerViewAdapter(this)
        recyclerView.adapter = medicalDepartmentAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        medicalDepartmentAdapter.submitList(departments)

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

    override fun onItemClick(position: Int) {
        val selectedHospitalName = arguments?.getString(HospitalName)
        val hospitalId = arguments?.getInt(HospitalId)
        val departmentName = departments[position].medicalDepartmentName
        val departmentId = departments[position].medicalDepartmentId

        val fragment = BookAppointmentFragment()
        val bundle = Bundle()
        bundle.putString("hospitalName", selectedHospitalName)
        bundle.putString("hospitalId", hospitalId.toString())
        bundle.putString("departmentName", departmentName)
        bundle.putString("departmentId", departmentId.toString())
        fragment.arguments = bundle

        (mActivity as MainActivity).replaceFragment(fragment, R.id.fragment_container, true)
    }

    private fun filter(text: String?) {
        val filteredList: MutableList<MedicalDepartment> = mutableListOf()

        for (item in departments) {
            if (text != null) {
                if (item.medicalDepartmentName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                    filteredList.add(item)
                }
            }
        }
        if (filteredList.isEmpty())
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()

        medicalDepartmentAdapter.submitList(filteredList)
    }
}
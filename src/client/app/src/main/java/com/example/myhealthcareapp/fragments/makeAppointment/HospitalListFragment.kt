package com.example.myhealthcareapp.fragments.makeAppointment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.HospitalRecyclerViewAdapter
import com.example.myhealthcareapp.api.MyHealthCareViewModel
import com.example.myhealthcareapp.constants.Constant.HospitalId
import com.example.myhealthcareapp.constants.Constant.HospitalName
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.Hospital
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HospitalListFragment : BaseFragment(), OnItemClickListener {
    private lateinit var hospitals: MutableList<Hospital>
    private lateinit var adapter : HospitalRecyclerViewAdapter
    private val viewModel by viewModel<MyHealthCareViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_hospital_list, container, false)
        viewModel.loadHospitals()
        viewModel.hospitals.observe(viewLifecycleOwner, { response ->
            if(response.isSuccessful) {
                Log.d("Hospitals", response.body()?.data.toString())
                hospitals = response.body()?.data as MutableList
                setupUI(view)
            }
        })
        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val email = (mActivity as MainActivity).mAuth.currentUser!!.email!!
//        (mActivity as MainActivity).fireStore.collection("users").document(email).get()
//            .addOnSuccessListener { document ->
//                if(document != null){
//                    val userId = (mActivity as MainActivity).mAuth.currentUser!!.uid
//                    val firstName = document.data!!["firstName"].toString()
//                    val lastName = document.data!!["lastName"].toString()
//                    val personalCode = document.data!!["personalCode"].toString()
//
//                    val client = Client(userId, firstName, lastName, email, personalCode)
//                    Cache.setClient(client)
//                    Log.d(ContentValues.TAG, client.toString())
//                    setupUI(view)
//                }
//                else{
//                    Log.d(ContentValues.TAG, "No such user")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(ContentValues.TAG, "Get failed with ", exception)
//            }
//    }

    private fun setupUI(view: View){
        (mActivity as MainActivity).topAppBar.visibility = View.VISIBLE
        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.select_hospital)
        (mActivity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (mActivity as MainActivity).searchIcon.isVisible = true
        (mActivity as MainActivity).profileIcon.isVisible = true

        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = HospitalRecyclerViewAdapter(hospitals, this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setHasFixedSize(true)

        (mActivity as MainActivity).searchView.queryHint = (mActivity).getString(R.string.hospital_hint)

        (mActivity as MainActivity).setProfileIconListener()

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
        val bundle = Bundle()
        bundle.putString(HospitalName, hospitals[position].hospitalName)
        bundle.putInt(HospitalId, hospitals[position].hospitalId)
        val fragment = MedicalDepartmentListFragment()
        fragment.arguments = bundle

        (mActivity as MainActivity).replaceFragment(fragment,R.id.fragment_container, true)
    }

    private fun filter(text: String?) {
        val filteredList: MutableList<Hospital> = mutableListOf()

        for (item in hospitals) {
            if (text != null) {
                if (item.hospitalName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {

                    filteredList.add(item)
                }
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
        }

        adapter.filterList(filteredList)
    }
}
package com.example.myhealthcareapp.makeAppointment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.HospitalRecyclerViewAdapter
import com.example.myhealthcareapp.cache.Cache
import com.example.myhealthcareapp.constants.Constant.HospitalId
import com.example.myhealthcareapp.constants.Constant.HospitalIdString
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.Hospital
import com.example.myhealthcareapp.models.user.Client
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.core.view.isVisible
import java.util.*


class HospitalListFragment : Fragment(), OnItemClickListener {
    private lateinit var button: Button
    private lateinit var exampleList: MutableList<Hospital>
    lateinit var adapter : HospitalRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hospital_list, container, false)

        val email = (activity as MainActivity).mAuth.currentUser!!.email!!
        (activity as MainActivity).fireStore.collection("users").document(email).get()
            .addOnSuccessListener { document ->
                if(document != null){
                    val userId = (activity as MainActivity).mAuth.currentUser!!.uid
                    val firstName = document.data!!["firstName"].toString()
                    val lastName = document.data!!["lastName"].toString()
                    val personalCode = document.data!!["personalCode"].toString()

                    val client = Client(userId, firstName, lastName, email, personalCode)
                    Cache.setClient(client)
                    Log.d(ContentValues.TAG, client.toString())
                    setupUI(view)
                }
                else{
                    Log.d(ContentValues.TAG, "No such user")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Get failed with ", exception)
            }

        return view
    }

    private fun setupUI(view: View){
        (activity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (activity as MainActivity).topAppBar.menu.findItem(R.id.search).isVisible = true
        (activity as MainActivity).topAppBar.menu.findItem(R.id.profile).isVisible = true

        exampleList = generateDummyList(20)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = HospitalRecyclerViewAdapter(exampleList, this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setHasFixedSize(true)

        val searchIcon = (activity as MainActivity).topAppBar.menu.findItem(R.id.search)

        searchIcon.setOnMenuItemClickListener{
            (activity as MainActivity).searchView.isFocusable = true
            (activity as MainActivity).searchView.isIconified = true
            (activity as MainActivity).searchView.isVisible = true
            (activity as MainActivity).topAppBar.isVisible = false

            (activity as MainActivity).searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    filter(query)
                    return true
                }
            })

            (activity as MainActivity).searchView.setOnCloseListener (object : SearchView.OnCloseListener {
                override fun onClose(): Boolean {
                    (activity as MainActivity).searchView.isVisible = false
                    (activity as MainActivity).topAppBar.isVisible = true

                    return true
                }
            })

            return@setOnMenuItemClickListener true
        }

    }

    private fun generateDummyList(size: Int): MutableList<Hospital> {

        val list : MutableList<Hospital> = mutableListOf()
        for (i in 0 until size) {
            val item = Hospital(i,"Policlinica 2_$i",
                "0744077777",
                "Bulevardul 1 Decembrie 1918, Târgu Mureș 540011",
                "asdf",
                "asdf",
                "asdf"
            )
            list += item
        }

        return list
    }

    override fun onItemClick(position: Int) {

        val bundle = Bundle()
        bundle.putString(HospitalIdString, exampleList[position].hospitalId.toString())
        bundle.putInt(HospitalId,exampleList[position].hospitalId)
        val fragment = MedicalDepartmentListFragment()
        fragment.arguments = bundle

        (activity as MainActivity).replaceFragment(fragment,R.id.fragment_container, true)
    }

    private fun filter(text: String?) {
        val filteredlist: MutableList<Hospital> = mutableListOf()

        for (item in exampleList) {
            // checking if the entered string matched with any item of our recycler view.
            if (text != null) {
                if (item.hospitalName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {

                    filteredlist.add(item)
                }
            }
        }
        if (filteredlist.isEmpty())
            Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()

        adapter.filterList(filteredlist)

    }
}
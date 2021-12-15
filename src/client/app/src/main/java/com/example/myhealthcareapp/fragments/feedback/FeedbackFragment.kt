package com.example.myhealthcareapp.fragments.feedback

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.FeedbackAdapter
import com.example.myhealthcareapp.api.MyHealthCareViewModel
import com.example.myhealthcareapp.cache.Cache
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.response.FeedBackAppointmentResponse
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedbackFragment : BaseFragment(), OnItemClickListener {
    private lateinit var feedbacks: MutableList<FeedBackAppointmentResponse>
    private lateinit var adapter : FeedbackAdapter
    private val viewModel by viewModel<MyHealthCareViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feedback, container, false)

        viewModel.feedbacks.observe(viewLifecycleOwner, { response ->
            if(response.isSuccessful){
                Log.d("feedbacks", response.body().toString())
                feedbacks = response.body()?.data as MutableList<FeedBackAppointmentResponse>
                setupUI(view)
            }
            else {
                Log.e("feedbacks", response.errorBody().toString())
            }
        })

        viewModel.getFeedbacks(Cache.getClient().id)

        return view
    }

    private fun setupUI(view : View){
        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.my_feedbacks)
        (mActivity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (mActivity as MainActivity).searchIcon.isVisible = false
        (mActivity as MainActivity).profileIcon.isVisible = true

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = FeedbackAdapter(feedbacks, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
    }


    override fun onItemClick(position: Int) {
        val singleFeedback = feedbacks[position]

        val summary = arrayOf(
            "Hospital: " + singleFeedback.appointment.hospitalName,
            "Department: " + singleFeedback.appointment.medicalDepartmentName,
            "Medic: " + singleFeedback.appointment.medicName,
            "Date & Time: " + singleFeedback.appointment.scheduleStartDate + ", " + singleFeedback.appointment.scheduleEndDate,
        )

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.summary))
            .setItems(summary) {_, _ ->}
            .show()
    }
}
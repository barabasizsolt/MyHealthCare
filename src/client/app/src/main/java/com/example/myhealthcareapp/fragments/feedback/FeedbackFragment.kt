package com.example.myhealthcareapp.fragments.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.MainActivity
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.adapters.FeedbackAdapter
import com.example.myhealthcareapp.fragments.BaseFragment
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.Feedback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class FeedbackFragment : BaseFragment(), OnItemClickListener {
    private lateinit var feedbacks: MutableList<Feedback>
    private lateinit var adapter : FeedbackAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feedback, container, false)

        feedbacks = generateDummyList(20)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = FeedbackAdapter(feedbacks, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        (mActivity as MainActivity).topAppBar.title = (mActivity).getString(R.string.my_feedbacks)
        (mActivity as MainActivity).bottomNavigation.visibility = View.VISIBLE
        (mActivity as MainActivity).searchIcon.isVisible = true
        (mActivity as MainActivity).profileIcon.isVisible = true

        return view
    }

    private fun generateDummyList(size: Int): MutableList<Feedback> {
        val list : MutableList<Feedback> = mutableListOf()
        for (i in 0 until size) {
            val item = Feedback(i,
                1,
                "Everything was ok",
                "660.00"
            )
            list += item
        }

        return list
    }

    override fun onItemClick(position: Int) {
        val summary = arrayOf(
            "Hospital: " + "Policlinica 2", //Hospital name,
            "Department: " + "Neurology", //Department name,
            "Medic: " + "Dr House1", //Medic name
            //"Date & Time: " + appointmentDate.text + "-" + appointmentDate.text.toString().takeLast(5), //Appointment date & time
            "Date & Time: " + "2021-12-21 16:30" + "-" + "17:00", //Appointment date & time
            "Notes: " + feedbacks[position].feedbackMessage
        )
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.summary))
            .setItems(summary) {_, _ ->}
            .show()
    }
}
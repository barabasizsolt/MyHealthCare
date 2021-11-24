package com.example.myhealthcareapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.Feedback
import kotlinx.android.synthetic.main.my_appointments_recyclerview_element.view.*

class FeedbackAdapter(private var feedbackList : MutableList<Feedback>, private val listener : OnItemClickListener) : RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>() {

    inner class FeedbackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val medicImage : ImageView = itemView.medic_image
        val medicName : TextView = itemView.medic_name
        val hospitalName : TextView = itemView.hospital_name
        val appointmentDate : TextView = itemView.appointment_date

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = this.absoluteAdapterPosition
            if( position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_appointments_recyclerview_element, parent, false)
        return FeedbackViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedbackAdapter.FeedbackViewHolder, position: Int)
    {
        val itemsViewModel = feedbackList[position]

        Glide.with(holder.itemView.context)
            .load(R.drawable.ic_avatar)
            .placeholder(R.drawable.ic_avatar)
            .error(R.drawable.ic_avatar)
            .circleCrop()
            .into(holder.medicImage)

        holder.medicName.text = "Dr House1"
        holder.hospitalName.text = "Policlinica 2"
        holder.appointmentDate.text = "2021-12-21 16:30-17:00"
    }


    override fun getItemCount(): Int = feedbackList.size

}
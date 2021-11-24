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
import com.example.myhealthcareapp.models.Appointment
import kotlinx.android.synthetic.main.my_appointments_recyclerview_element.view.*

class MyAppointmentsAdapter(private var appointmentList : MutableList<Appointment>, private val listener : OnItemClickListener) : RecyclerView.Adapter<MyAppointmentsAdapter.MyAppointmentsViewHolder>() {

    inner class MyAppointmentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAppointmentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_appointments_recyclerview_element, parent, false)
        return MyAppointmentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAppointmentsViewHolder, position: Int) {
        val itemsViewModel = appointmentList[position]

        Glide.with(holder.itemView.context)
            .load(R.drawable.ic_avatar)
            .placeholder(R.drawable.ic_avatar)
            .error(R.drawable.ic_avatar)
            .circleCrop()
            .into(holder.medicImage)

        holder.medicName.text = "Dr House1"
        holder.hospitalName.text = "Policlinica 2"
        holder.appointmentDate.text = itemsViewModel.scheduleEndDate + "-" + itemsViewModel.scheduleEndDate.takeLast(5)

    }

    override fun getItemCount(): Int = appointmentList.size
}
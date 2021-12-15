package com.example.myhealthcareapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.models.response.MakeAppointment
import kotlinx.android.synthetic.main.medic_appointment_element.view.*

class MedicAppointmentAdapter(private var appointmentList : MutableList<MakeAppointment>) : RecyclerView.Adapter<MedicAppointmentAdapter.MedicAppointmentViewHolder>() {

    inner class MedicAppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val patientName : TextView = itemView.patient_name
        val medicalDepartmentName : TextView = itemView.department_name
        val appointmentStartDate : TextView = itemView.appointment_start_date
        private val detailsButton : Button = itemView.details_button

        init {
            detailsButton.setOnClickListener {
                //TODO: pop up dialog with appointment info
                Toast.makeText(itemView.context, "Item clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicAppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medic_appointment_element, parent, false)
        return MedicAppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicAppointmentViewHolder, position: Int) {
        val itemsViewModel = appointmentList[position]
        holder.patientName.text = "Samuel John Doe"
        holder.medicalDepartmentName.text = "Cardiology"
        holder.appointmentStartDate.text = "2021-12-21 16:30"
    }

    override fun getItemCount(): Int = appointmentList.size
}
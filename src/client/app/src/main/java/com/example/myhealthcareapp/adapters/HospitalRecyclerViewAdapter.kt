package com.example.myhealthcareapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.models.Hospital
import kotlinx.android.synthetic.main.make_appointment_recyclerview_element.view.*

class HospitalRecyclerViewAdapter(private val hospitalList : MutableList<Hospital>)  : RecyclerView.Adapter<HospitalRecyclerViewAdapter.HospitalRecyclerViewViewHolder>() {

    inner class HospitalRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hospitalImage : ImageView = itemView.hospital_image
        val hospitalName : TextView = itemView.hospital_name
        val hospitalPhoneNumber : TextView = itemView.hospital_phone_number
        val hospitalAddress : TextView = itemView.hospital_address

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HospitalRecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.make_appointment_recyclerview_element, parent, false)

        return HospitalRecyclerViewViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: HospitalRecyclerViewViewHolder, position: Int) {
        val currentHospital = hospitalList[position]

        Glide.with(holder.itemView.context)
            .load(R.drawable.hospital_icon)
            .into(holder.hospitalImage)


        holder.hospitalName.text = currentHospital.hospitalName
        holder.hospitalPhoneNumber.text = currentHospital.hospitalPhoneNumber
        holder.hospitalAddress.text = currentHospital.hospitalAddress
    }

    override fun getItemCount(): Int = hospitalList.size

}
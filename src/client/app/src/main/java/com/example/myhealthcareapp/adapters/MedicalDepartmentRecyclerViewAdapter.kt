package com.example.myhealthcareapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.Hospital
import com.example.myhealthcareapp.models.MedicalDepartment
import kotlinx.android.synthetic.main.medical_department_recyclerview_element.view.*

class MedicalDepartmentRecyclerViewAdapter(private var medicalDepartmentList: MutableList<MedicalDepartment>, private val listener : OnItemClickListener)  : RecyclerView.Adapter<MedicalDepartmentRecyclerViewAdapter.MedicalDepartmentRecyclerViewViewHolder>() {

    inner class MedicalDepartmentRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val medicalDepartmentImage : ImageView = itemView.medical_department_image
        val medicalDepartmentName : TextView = itemView.medical_department_name
        val medicalDepartmentPhoneNumber : TextView = itemView.medical_department_phone_number
        val medicalDepartmentAddress : TextView = itemView.medical_department_address

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalDepartmentRecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.medical_department_recyclerview_element,parent,false)
        return MedicalDepartmentRecyclerViewViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MedicalDepartmentRecyclerViewViewHolder, position: Int) {
        val currentMedicalDepartment = medicalDepartmentList[position]

        Glide.with(holder.itemView.context)
            .load(R.drawable.hospital_icon)
            .into(holder.medicalDepartmentImage)

        holder.medicalDepartmentName.text = currentMedicalDepartment.medicalDepartmentName
        holder.medicalDepartmentPhoneNumber.text = currentMedicalDepartment.medicalDepartmentPhoneNumber
        holder.medicalDepartmentAddress.text = currentMedicalDepartment.medicalDepartmentAddress
    }

    override fun getItemCount(): Int = medicalDepartmentList.size

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: MutableList<MedicalDepartment>) {
        // below line is to add our filtered list
        this.medicalDepartmentList = filterList
        // below line is to notify our adapter as change in recycler view data
        notifyDataSetChanged()
    }
}
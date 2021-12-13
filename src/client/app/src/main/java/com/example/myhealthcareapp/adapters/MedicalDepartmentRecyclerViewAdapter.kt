package com.example.myhealthcareapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.MedicalDepartment
import kotlinx.android.synthetic.main.medical_department_recyclerview_element.view.*

class MedicalDepartmentRecyclerViewAdapter(
    private val listener : OnItemClickListener
    )
    : ListAdapter<MedicalDepartment, MedicalDepartmentRecyclerViewAdapter.MedicalDepartmentRecyclerViewViewHolder>(
        object: DiffUtil.ItemCallback<MedicalDepartment>(){
            override fun areItemsTheSame(
                oldItem: MedicalDepartment,
                newItem: MedicalDepartment
            ) = oldItem.medicalDepartmentId == newItem.medicalDepartmentId

            override fun areContentsTheSame(
                oldItem: MedicalDepartment,
                newItem: MedicalDepartment
            ) = oldItem == newItem
        }
    )
{

    inner class MedicalDepartmentRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val medicalDepartmentImage : ImageView = itemView.medical_department_image
        val medicalDepartmentName : TextView = itemView.medical_department_name
        val medicalDepartmentPhoneNumber : TextView = itemView.medical_department_phone_number

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
        val currentMedicalDepartment = getItem(position)

        if(position % 2 == 0){
            Glide.with(holder.itemView.context)
                .load(R.drawable.department_v1)
                .into(holder.medicalDepartmentImage)
        }
        else{
            Glide.with(holder.itemView.context)
                .load(R.drawable.department_v2)
                .into(holder.medicalDepartmentImage)
        }

        holder.medicalDepartmentName.text = currentMedicalDepartment.medicalDepartmentName
        holder.medicalDepartmentPhoneNumber.text = currentMedicalDepartment.medicalDepartmentPhoneNumber
    }
}
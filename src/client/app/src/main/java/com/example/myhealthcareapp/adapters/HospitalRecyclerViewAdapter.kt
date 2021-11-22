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
import kotlinx.android.synthetic.main.hospital_recyclerview_element.view.*

class HospitalRecyclerViewAdapter(private var hospitalList : MutableList<Hospital>, private val listener : OnItemClickListener)  : RecyclerView.Adapter<HospitalRecyclerViewAdapter.HospitalRecyclerViewViewHolder>() {

    inner class HospitalRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val hospitalImage : ImageView = itemView.hospital_image
        val hospitalName : TextView = itemView.hospital_name
        val hospitalPhoneNumber : TextView = itemView.hospital_phone_number
        val hospitalAddress : TextView = itemView.hospital_address

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

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: MutableList<Hospital>) {
        // below line is to add our filtered list
        this.hospitalList = filterList
        // below line is to notify our adapter as change in recycler view data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalRecyclerViewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hospital_recyclerview_element,parent,false)
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
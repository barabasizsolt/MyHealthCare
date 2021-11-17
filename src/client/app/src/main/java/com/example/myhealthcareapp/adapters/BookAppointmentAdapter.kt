package com.example.myhealthcareapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.models.Medic

class BookAppointmentAdapter(private val mList: List<Medic>) : RecyclerView.Adapter<BookAppointmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medic_recyclerview_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        //holder.medicImage
        holder.medicName.text = itemsViewModel.name
    }

    override fun getItemCount() = mList.size

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val medicImage: ImageView = itemView.findViewById(R.id.medic_image)
        val medicName: TextView = itemView.findViewById(R.id.medic_name)
    }
}
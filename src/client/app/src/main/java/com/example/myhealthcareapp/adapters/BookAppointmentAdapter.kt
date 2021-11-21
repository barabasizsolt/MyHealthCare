package com.example.myhealthcareapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myhealthcareapp.R
import com.example.myhealthcareapp.interfaces.OnItemClickListener
import com.example.myhealthcareapp.models.Medic

class BookAppointmentAdapter(private val mList: List<Medic>, private val listener : OnItemClickListener) : RecyclerView.Adapter<BookAppointmentAdapter.ViewHolder>() {
    private var selectedPosition = 0;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medic_recyclerview_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        //holder.medicImage

        Glide.with(holder.itemView.context)
            .load(R.drawable.ic_avatar)
            .placeholder(R.drawable.ic_avatar)
            .error(R.drawable.ic_avatar)
            .circleCrop()
            .into(holder.medicImage)

        holder.medicName.text = itemsViewModel.name
        if(position == selectedPosition){
            holder.background.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorPrimary))
        }
        else{
            holder.background.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorHintText))
        }
    }

    override fun getItemCount() = mList.size

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView), View.OnClickListener {
        val medicImage: ImageView = itemView.findViewById(R.id.medic_image)
        val medicName: TextView = itemView.findViewById(R.id.medic_name)
        val background: CardView = itemView.findViewById(R.id.medic_card_view)

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
    fun selectMedic(position: Int){
       selectedPosition = position
        notifyDataSetChanged()
    }
}
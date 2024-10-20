package com.example.agendaodonto.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaodonto.models.Dentista
import com.example.agendaodonto.R
import com.bumptech.glide.Glide
import com.example.agendaodonto.AgendarCalendarioActivity
import com.example.agendaodonto.DoctorProfileActivity
import me.zhanghai.android.materialratingbar.MaterialRatingBar


class DentistaAdapter(private val doctorList: List<Dentista>) :
    RecyclerView.Adapter<DentistaAdapter.DoctorViewHolder>() {

    class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doctorName: TextView = view.findViewById(R.id.tv_doctor_name)
        val doctorSpecialty: TextView = view.findViewById(R.id.tv_specialty)
        val doctorRating: TextView = view.findViewById(R.id.tv_doctor_rating)
        val doctorAvatar: ImageView = view.findViewById(R.id.iv_doctor_avatar)
        val ratingBar: MaterialRatingBar = view.findViewById(R.id.rb_rating)
        val btnCardMenu: Button = view.findViewById(R.id.btn_card_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_dentista, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctorList[position]
        holder.doctorName.text = doctor.name
        holder.doctorSpecialty.text = doctor.specialty
        holder.doctorRating.text = doctor.rating.toString()
        holder.ratingBar.rating = doctor.rating

        val imageUrl = "https://i.pravatar.cc/200?uniqueParam=${System.currentTimeMillis()}"

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.doctorAvatar)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, AgendarCalendarioActivity::class.java)
            intent.putExtra("doctorName", doctor.name)  // Pass the doctor's name
            context.startActivity(intent)
        }

        holder.btnCardMenu.setOnClickListener { v ->
            showCardMenu(v, R.menu.card_dentista_menu, doctor)
        }
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    private fun showCardMenu(view: View, @MenuRes menuRes: Int, doctor: Dentista) {
        val popup = PopupMenu(view.context, view)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.option_1 -> { // First option in the popup menu
                    val context = view.context
                    val intent = Intent(context, DoctorProfileActivity::class.java)

                    // Pass the doctor's details to the profile activity
                    intent.putExtra("doctorName", doctor.name)
                    intent.putExtra("doctorSpecialty", doctor.specialty)
                    intent.putExtra("doctorRating", doctor.rating)

                    context.startActivity(intent)
                    true
                }

                else -> false
            }
        }
        popup.show()
    }
}
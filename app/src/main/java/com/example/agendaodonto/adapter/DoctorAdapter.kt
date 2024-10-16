package com.example.agendaodonto

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaodonto.AgendarActivity
import com.example.agendaodonto.Doctor
import com.example.agendaodonto.R

class DoctorAdapter(private val doctorList: List<Doctor>) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    // ViewHolder para armazenar as views do card
    class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doctorName: TextView = view.findViewById(R.id.tv_doctor_name)
        val doctorSpecialty: TextView = view.findViewById(R.id.tv_specialty)
        val doctorRating: TextView = view.findViewById(R.id.tv_doctor_rating)
        val doctorAvatar: ImageView = view.findViewById(R.id.iv_doctor_avatar)
        val ratingStar: ImageView = view.findViewById(R.id.iv_star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_doctor, parent, false) // Inflando o layout do card de médico
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctorList[position]

        // Definindo o conteúdo de cada card de médico
        holder.doctorName.text = doctor.name
        holder.doctorSpecialty.text = doctor.specialty
        holder.doctorRating.text = doctor.rating.toString()

        // Exemplo de avatar (você pode usar Glide ou Picasso para carregar de uma URL)
        holder.doctorAvatar.setImageResource(R.drawable.avatar_background)

        // Definir o clique no card para abrir a tela de agendamento (AgendarActivity)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AgendarActivity::class.java)
            intent.putExtra("doctorId", doctor.id) // Enviando o ID do médico selecionado para a AgendarActivity
            intent.putExtra("doctorName", doctor.name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }
}

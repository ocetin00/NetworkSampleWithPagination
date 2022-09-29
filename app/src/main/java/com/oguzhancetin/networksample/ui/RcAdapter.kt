package com.oguzhancetin.networksample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oguzhancetin.networksample.databinding.CivilizationCardBinding
import com.oguzhancetin.networksample.model.Civilization

class RcAdapter(private var civilizations: List<Civilization>) :
    RecyclerView.Adapter<RcAdapter.CivilizationViewHolder>() {

    inner class CivilizationViewHolder(val binding: CivilizationCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CivilizationViewHolder {
        val binding =
            CivilizationCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CivilizationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CivilizationViewHolder, position: Int) {
        holder.binding.textViewCivilizationName.text = civilizations[position].name
    }

    override fun getItemCount(): Int =
        civilizations.size

    fun updateData(civilizations: List<Civilization>){
        this.civilizations = civilizations
        notifyDataSetChanged()
    }
}
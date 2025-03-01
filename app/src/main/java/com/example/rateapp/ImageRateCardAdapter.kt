package com.example.rateapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ImageRateCardAdapter(
    private val items: List<ImageRateCardData>,
    private val onItemSelected: (Int, Int) -> Unit
) : RecyclerView.Adapter<ImageRateCardAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageRateCard: ImageRateCard = itemView.findViewById(R.id.imageRateCard)

        fun bind(item: ImageRateCardData) {
            imageRateCard.setImage(item.imageResId)
            imageRateCard.setName(item.imageName)

            imageRateCard.setOnValueSelectedListener { selectedValue ->
                item.selectedValue = selectedValue
                onItemSelected(adapterPosition, selectedValue)
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ImageDescriptionActivity::class.java).apply {
                    putExtra("image_id", item.imageResId)
                    putExtra("description", item.imageDescription)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_rate_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
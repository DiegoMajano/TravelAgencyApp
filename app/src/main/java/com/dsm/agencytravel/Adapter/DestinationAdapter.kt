package com.dsm.agencytravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsm.agencytravel.model.Destination

class DestinationAdapter(
    private val list: List<Destination>,
    private val onEdit: (Destination) -> Unit,
    private val onDelete: (Destination) -> Unit
) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txtName)
        val price: TextView = view.findViewById(R.id.txtPrice)
        val description: TextView = view.findViewById(R.id.txtDescription)
        val image: ImageView = view.findViewById(R.id.imgDestination)

        val btnEdit: View = view.findViewById(R.id.btnEdit)
        val btnDelete: View = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = list[position]

        holder.name.text = destination.name
        holder.price.text = "$${destination.price}"
        holder.description.text = destination.description

        Glide.with(holder.itemView.context)
            .load(destination.imageUrl)
            .into(holder.image)

        holder.btnEdit.setOnClickListener {
            onEdit(destination)
        }

        holder.btnDelete.setOnClickListener {
            onDelete(destination)
        }
    }
}
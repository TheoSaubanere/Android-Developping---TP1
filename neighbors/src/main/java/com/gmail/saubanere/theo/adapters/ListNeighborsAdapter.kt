package com.gmail.saubanere.theo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.saubanere.theo.neighbors.fragments.R
import com.gmail.saubanere.theo.neightbors.models.Neighbor

class ListNeighborsAdapter(
    items: List<Neighbor>,
    handler: ListNeighborHandler
) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {
    private val mNeighbours: List<Neighbor> = items
    private val mHandler: ListNeighborHandler = handler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.neighbor_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]
        val context = holder.mNeighbourAvatar.context

        // Display Neighbour Name
        holder.mNeighbourName.text = neighbour.name

        // Display Neighbour Avatar
        Glide.with(context)
            .load(neighbour.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_person_outline_24)
            .error(R.drawable.ic_baseline_person_outline_24)
            .skipMemoryCache(false)
            .into(holder.mNeighbourAvatar)

        // Delete Neighbour
        holder.mDeleteButton.setOnClickListener {
            mHandler.onDeleteNeighbor(neighbour)
        }
    }

    override fun getItemCount(): Int {
        return mNeighbours.size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mNeighbourAvatar: ImageView = view.findViewById(R.id.item_list_avatar)
        val mNeighbourName: TextView = view.findViewById(R.id.item_list_name)
        val mDeleteButton: ImageButton = view.findViewById(R.id.item_list_delete_button)
    }
}

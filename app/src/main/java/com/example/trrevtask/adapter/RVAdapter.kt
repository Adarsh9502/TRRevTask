package com.example.trrevtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trrevtask.R
import com.example.trrevtask.model.Post

class RVAdapter: RecyclerView.Adapter<RVAdapter.RvViewHolder>() {

    private var list = emptyList<Post>()
    inner class RvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        var deptId: TextView = itemView.findViewById(R.id.deptId)
        var name: TextView = itemView.findViewById(R.id.name)
        var createdAt: TextView = itemView.findViewById(R.id.created_at)
        var updatedAt: TextView = itemView.findViewById(R.id.updated_at)
        var image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.deptId.text = list[position].id.toString()
        holder.name.text = list[position].name
        holder.createdAt.text = list[position].created_at
        holder.updatedAt.text = list[position].updated_at
        Glide.with(holder.image.context)
            .load(list[position].url)
            .circleCrop()
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newList: List<Post>) {
        list = newList
        notifyDataSetChanged()
    }
}
package com.example.sept

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter( val context: Context, val item : MutableList<FlexItem>, val listener: (FlexItem) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: RecyclerAdapter.MainHolder, position: Int) {
        holder.bind(item.get(position))
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val ContentNews : TextView = itemView.findViewById(R.id._title)
        private val NewsImage : ImageView = itemView.findViewById(R.id.Fleximage)
        fun bind(item : FlexItem) {
            ContentNews.text = "Новость: " + item.content
            Picasso.get().load(item.urlimg).fit().placeholder(R.drawable.black).error(R.drawable.black).into(NewsImage)
            itemView.setOnClickListener{listener(item)}
        }

    }

}


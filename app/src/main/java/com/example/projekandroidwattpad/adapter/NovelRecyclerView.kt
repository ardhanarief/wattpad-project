package com.example.projekandroidwattpad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projekandroidwattpad.R
import com.example.projekandroidwattpad.model.Novel
import kotlinx.android.synthetic.main.list_item.view.*

class NovelRecyclerView(
    val listNovel: List<Novel>
) : RecyclerView.Adapter<NovelRecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listNovel[position]

        holder.itemView.apply {
            Glide.with(context).load(currentItem.coverNovel).into(iv_item_cover)
            tv_item_judul.text = currentItem.judulNovel
            tv_item_genre.text = currentItem.genreNovel
            tv_item_deskripsi.text = ""
            setOnClickListener {
                onItemClickListener?.let {
                    it(currentItem)
                }
            }
        }
    }

    private var onItemClickListener: ((Novel) -> Unit)? = null

    fun setOnItemClickListener(listener: (Novel) -> Unit) {
        onItemClickListener = listener
    }


    override fun getItemCount(): Int = listNovel.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
package com.example.recyclerviewretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewretrofit.news.Article
import com.example.recyclerviewretrofit.news.Source
import com.example.recyclerviewretrofit.news.Welcome

class RetrofitRecyclerAdapter(private val data: ArrayList<Article>, private val onClickListner: SetOnClickListner)
    :RecyclerView.Adapter<RetrofitRecyclerAdapter.Viewholder>() {

    class Viewholder(view:View):RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.newImageView)
        val author: TextView = view.findViewById(R.id.authorTextView)
        val title: TextView = view.findViewById(R.id.titleTextView)
        val name: TextView = view.findViewById(R.id.nameTextView)
    }

    interface SetOnClickListner{
        fun onItemCellClick(item: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.retrofit_recycler_adapter,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val setData = data[position]
        holder.author.text = setData.author
        holder.title.text = setData.title
        holder.name.text = setData.source.name

        if (setData.urlToImage != null){
            Glide.with(holder.itemView)
                .load(setData.urlToImage)
                .into(holder.img)
        }else{
            holder.img.setImageResource(R.drawable.noimage)
        }

        holder.itemView.setOnClickListener {
            onClickListner.onItemCellClick(setData)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
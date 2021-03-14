//package com.example.recyclerviewretrofit
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class RecyclerAdapter(private val data: ArrayList<RecyclerData>):RecyclerView.Adapter<RecyclerAdapter.Viewholder>(){
//    class Viewholder(view:View):RecyclerView.ViewHolder(view) {
//        var number = view.findViewById<TextView>(R.id.numberText)
//        var text = view.findViewById<TextView>(R.id.textText)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false)
//        return Viewholder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: Viewholder, position: Int) {
//        val fillData = data[position]
//        holder.number.text =fillData.number.toString()
//        holder.text.text = fillData.text
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//}
package com.example.mymusicapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context:Activity,val datalist:List<Data>):

    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private lateinit var listener:OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)

    }
    fun setOnItemClickListener(mlistener: OnItemClickListener){
        listener=mlistener
    }

    class MyViewHolder(itemView: View,listener:OnItemClickListener):RecyclerView.ViewHolder(itemView) {
        val musimage:ImageView=itemView.findViewById(R.id.musicimage)
        val mutitle:TextView=itemView.findViewById(R.id.musictitle)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(view,listener)


    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem=datalist[position]
        holder.mutitle.text=currentitem.title
        Picasso.get().load(currentitem.album.cover).into(holder.musimage);



    }
}
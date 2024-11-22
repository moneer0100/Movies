package com.example.movieapp.ui.fav.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemFavBinding
import com.example.movieapp.databinding.TrendingItemBinding
import com.example.movieapp.model.pojo.Result


class FavoiriteAdapter(private val onItemClick:(Result)->Unit
      ,private val deletitem:(Result)->Unit)
    :ListAdapter<Result,FavoiriteAdapter.ViewHolder>(MyDifitul()) {
    class MyDifitul:DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
          return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
         return oldItem==newItem
        }

    }
    class ViewHolder(val binding: ItemFavBinding):RecyclerView.ViewHolder(binding.root){

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater=LayoutInflater.from(parent.context)
         val binding =ItemFavBinding.inflate(inflater,parent,false)
         return ViewHolder(binding)
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val fav: Result =getItem(position)
         holder.binding.titleid.text=fav.title
         Glide.with(holder.itemView.context)
             .load("https://image.tmdb.org/t/p/w300${fav.posterPath}")
             .placeholder(R.drawable.video)
             .error(R.drawable.error)
             .into(holder.binding.imagemoviid)
         holder.itemView.setOnClickListener {
             val discoverMovies=fav
             onItemClick(discoverMovies)
         }

       holder.binding.deletid.setOnClickListener {

           deletitem(fav)
       }
     }
     }

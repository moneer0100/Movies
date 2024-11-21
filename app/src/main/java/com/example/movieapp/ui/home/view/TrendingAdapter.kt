package com.example.movieapp.ui.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.TrendingItemBinding
import com.example.movieapp.model.pojo.Result
class TrendingAdapter(private val onitemClick: (Result) -> Unit ):ListAdapter<Result,TrendingAdapter.ViewHolder>(MyDifituil()) {
    private lateinit var binding: TrendingItemBinding
    class MyDifituil:DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
return oldItem ==newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
  return oldItem==newItem
        }

    }
    class ViewHolder( var binding: TrendingItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflat:LayoutInflater=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
binding= TrendingItemBinding.inflate(inflat,parent,false)
return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trendingMovie: Result =getItem(position)
        Glide.with(holder.itemView.context)
            ?.load("https://image.tmdb.org/t/p/w342${trendingMovie?.posterPath}")
            ?.placeholder(R.drawable.video)
            ?.error(R.drawable.error)  // Base URL + posterPath
            ?.into(holder.binding.imageTrendingid)
        holder.itemView.setOnClickListener {
            val trendingMovie = trendingMovie
            onitemClick(trendingMovie)
        }



    }}
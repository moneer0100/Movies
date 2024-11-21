package com.example.movieapp.ui.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.TrendingItemBinding
import com.example.movieapp.model.pojo.Result

class DiscoverMoviesAdapter (private val onItemClick :(Result)-> Unit): ListAdapter<Result, DiscoverMoviesAdapter.ViewHolder>(MyDifitiul()){
    private lateinit var binding: TrendingItemBinding
    class MyDifitiul: DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return newItem==oldItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return newItem==oldItem
        }

    }
    class ViewHolder( val binding: TrendingItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = TrendingItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val discoverMovies: Result =getItem(position)
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w342${discoverMovies.posterPath}")
            .placeholder(R.drawable.video) // صورة مؤقتة أثناء التحميل
            .error(R.drawable.error) // صورة عند حدوث خطأ في التحميل
            .into(holder.binding.imageTrendingid) // ImageView المستهدف
            holder.itemView.setOnClickListener {
                val discoverMovies=discoverMovies
                onItemClick(discoverMovies)
            }
        // إعداد النصوص الأخرى (إن وجدت)
    }
}
package com.example.movieapp.ui.search.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemSearchBinding
import com.example.movieapp.model.pojo.Result

class SearchAdapter(private val onitemClick: (Result) -> Unit ):ListAdapter<Result,SearchAdapter.ViewHolder>(MyDifitul()) {

    class MyDifitul:DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem==newItem
        }

    }
    class ViewHolder(val binding: ItemSearchBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val inflat=LayoutInflater.from(parent.context)
        val binding=ItemSearchBinding.inflate(inflat,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val search: Result = getItem(position)
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w342${search.posterPath}")
            .placeholder(R.drawable.video)
            .error(R.drawable.error)
            .into(holder.binding.serchImg)
        holder.itemView.setOnClickListener {
            val serchMovie = search
            onitemClick(serchMovie)
        }
    }
}
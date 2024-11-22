package com.example.movieapp.ui.home.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.model.db.DatabaseClient
import com.example.movieapp.model.db.LocalDataMovieImp
import com.example.movieapp.model.network.MovieRemoteImp
import com.example.movieapp.model.network.RetrofitHelper
//import com.example.movieapp.model.pojo.MoviePojo
import com.example.movieapp.model.pojo.RepoMovieImp
import com.example.movieapp.model.pojo.Result
import com.example.movieapp.ui.home.viewModel.HomeViewFactory
import com.example.movieapp.ui.home.viewModel.HomeViewModel
import com.google.firebase.inject.Provider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener


class Details : Fragment() {
    private lateinit var binding:FragmentDetailsBinding
private val API_KEY="AIzaSyCdAVi5z_C1WStIlghm-4GiaKJWpNguOAg"
    private val VIDEO_URL ="https://youtube.googleapis.com/youtube/v3/search?"

    private val viewModel: HomeViewModel by viewModels {
        HomeViewFactory(
            RepoMovieImp.getInstance(
                MovieRemoteImp.getInstance(RetrofitHelper.service)
                , LocalDataMovieImp.getInstance(
                    DatabaseClient
                    .getInstance(requireContext()).movieApp()
                ))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = DetailsArgs.fromBundle(requireArguments())
        val movie = args.data

        binding.apply {
          ratingBar.rating= movie?.voteCount?.toFloat()!!
            overviewTitleTxtView.text = movie.title
            overviewTxtView.text = movie.overview


            binding.addToWatchBtn.setOnClickListener {
          movie?.let { movieResult:Result ->

              val result = Result(
                  id = movieResult.id,
                  title = movieResult.title,
                  posterPath = movieResult.posterPath,
                  adult = movieResult.adult,
                  video = movieResult.video,
                  overview = movieResult.overview,
                  voteCount = movieResult.voteCount


              )
              Log.d("Fav", "onViewCreated:$movieResult ")
              viewModel.addMovieDatabase(result)
              Toast.makeText(
                  requireContext(),
                  "Added to Watchlist: ${movieResult.title}",
                  Toast.LENGTH_SHORT
              ).show()
          }
      }

    }


        }}


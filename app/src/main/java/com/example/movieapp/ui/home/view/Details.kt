package com.example.movieapp.ui.home.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.model.db.DatabaseClient
import com.example.movieapp.model.db.LocalDataMovieImp
import com.example.movieapp.model.network.MovieRemoteImp
import com.example.movieapp.model.network.RetrofitHelper
import com.example.movieapp.model.pojo.MoviePojo
import com.example.movieapp.model.pojo.RepoMovieImp
import com.example.movieapp.model.pojo.Result
import com.example.movieapp.ui.home.viewModel.HomeViewFactory
import com.example.movieapp.ui.home.viewModel.HomeViewModel


class Details : Fragment() {
    private lateinit var binding:FragmentDetailsBinding
private val API_KEY="AIzaSyCdAVi5z_C1WStIlghm-4GiaKJWpNguOAg"
    private val VIDEO="https://youtube.googleapis.com/youtube/v3/search?"
    private var movie: MoviePojo? = null
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
        }
      binding.addToWatchBtn.setOnClickListener {
          movie?.let { movieResult:Result ->
              // تأكد من إرسال المتغير الصحيح إلى الـ ViewModel
              val result = Result(
                  id = movieResult.id,
                  title = movieResult.title,
                  backdropPath = movieResult.backdropPath,
                  adult = movieResult.adult,
                  video = movieResult.video,
                  voteAverage = movieResult.voteAverage,
                  voteCount = movieResult.voteCount
                  // أضف الحقول الأخرى كما هو مناسب
              )
              viewModel.addMovieDatabase(result)
          }
      }
    }


        }


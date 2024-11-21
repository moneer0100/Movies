package com.example.movieapp.ui.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.model.db.DatabaseClient
import com.example.movieapp.model.db.LocalDataMovieImp
import com.example.movieapp.model.network.MovieRemoteImp
import com.example.movieapp.model.network.ResponsState
import com.example.movieapp.model.network.RetrofitHelper
import com.example.movieapp.model.pojo.RepoMovieImp
import com.example.movieapp.ui.home.viewModel.HomeViewFactory
import com.example.movieapp.ui.home.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var topRateAdapter: TopRateAdapter
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var discoverMoviesAdapter: DiscoverMoviesAdapter

    private val binding get() = _binding!!
    private val viewModel:HomeViewModel by viewModels {
        HomeViewFactory(
            RepoMovieImp.getInstance(
                MovieRemoteImp.getInstance(RetrofitHelper.service)
                ,LocalDataMovieImp.getInstance(DatabaseClient
                    .getInstance(requireContext()).movieApp()
            ))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trendingAdapter = TrendingAdapter { movie ->
            viewModel.getTrendingMovie()
                val action = HomeFragmentDirections.actionNavigationHomeToDetails2(movie)
                findNavController().navigate(action)
            }

        binding.recyclerTrending.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=trendingAdapter


        }
        topRateAdapter=TopRateAdapter{movie->
            viewModel.getTopRate()
            val action=HomeFragmentDirections.actionNavigationHomeToDetails2(movie)
            findNavController().navigate(action)
        }
        binding.recyclerTopRated.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=topRateAdapter

        }
        popularMoviesAdapter=PopularMoviesAdapter{movie->
            viewModel.getPopularMovies()
            val action=HomeFragmentDirections.actionNavigationHomeToDetails2(movie)
            findNavController().navigate(action)
        }
        binding.recyclerPopular.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=popularMoviesAdapter
        }
        discoverMoviesAdapter=DiscoverMoviesAdapter{movie->
            viewModel.getDiscoveMovie()
            val action=HomeFragmentDirections.actionNavigationHomeToDetails2(movie)
            findNavController().navigate(action)
        }
        binding.recyclerViewDiscover.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter=discoverMoviesAdapter
        }
        fetchTrendingMovie()
        fetchTopRate()
        featchPopularMovies()
        featchDiscoverMovie()
    }

    private fun featchDiscoverMovie() {
        viewModel.getDiscoveMovie()
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.dicoverMovie.collect{state->
                when(state){
                    is ResponsState.Loading->{
                        Log.d("popular", "featchPopularMovies:$state ")
                    }
                    is ResponsState.Error->{
                        Log.d("popular", "featchPopularMovies:${state.message} ")
                    }
                    is ResponsState.Success->{
                        discoverMoviesAdapter.submitList(state.data.results)
                        Log.d("popular", "featchPopularMovies: ${state.data}")
                    }
                }
            }
        }
    }

    private fun featchPopularMovies() {
      viewModel.getPopularMovies()
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.popularMovies.collect{state->
                when(state){
                    is ResponsState.Loading->{
                        Log.d("popular", "featchPopularMovies:$state ")
                    }
                    is ResponsState.Error->{
                        Log.d("popular", "featchPopularMovies:${state.message} ")
                    }
                    is ResponsState.Success->{
                        popularMoviesAdapter.submitList(state.data.results)
                        Log.d("popular", "featchPopularMovies: ${state.data}")
                    }
                }
            }
        }
    }

    fun fetchTrendingMovie(){
        viewModel.getTrendingMovie()
lifecycleScope.launch (Dispatchers.Main){
viewModel.trending.collect{state->
    when(state){
        is ResponsState.Loading->{
            Log.d("trending", "fetchTrendingMovie:$state ")
        }
        is ResponsState.Error->{
            Log.d("trending", "fetchTrendingMovie: ${state.message}")
        }
        is ResponsState.Success->{
            trendingAdapter.submitList(state.data.results)
            Log.d("trending", "fetchTrendingMovie:${state.data} ")
        }
    }
}
}
    }
    fun fetchTopRate(){
        viewModel.getTopRate()
        lifecycleScope.launch (Dispatchers.Main){
            viewModel.topRated.collect{state->
                when(state){
                    is ResponsState.Loading->{
                        Log.d("trending", "fetchTrendingMovie:$state ")
                    }
                    is ResponsState.Error->{
                        Log.d("trending", "fetchTrendingMovie: ${state.message}")
                    }
                    is ResponsState.Success->{
                        topRateAdapter.submitList(state.data.results)
                        Log.d("trending", "fetchTrendingMovie:${state.data} ")
                    }
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
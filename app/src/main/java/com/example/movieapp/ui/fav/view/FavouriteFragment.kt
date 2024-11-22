package com.example.movieapp.ui.fav.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentFavouriteBinding
import com.example.movieapp.model.db.DatabaseClient
import com.example.movieapp.model.db.LocalDataMovieImp
import com.example.movieapp.model.network.MovieRemoteImp
import com.example.movieapp.model.network.ResponsState
import com.example.movieapp.model.network.RetrofitHelper
import com.example.movieapp.model.pojo.RepoMovieImp
import com.example.movieapp.model.pojo.Result
import com.example.movieapp.ui.fav.viewModel.FavouritFactory
import com.example.movieapp.ui.fav.viewModel.FavouriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
private lateinit var favoiriteAdapter:FavoiriteAdapter
    private val binding get() = _binding!!
    private val viewModel:FavouriteViewModel by viewModels {
        FavouritFactory(RepoMovieImp.getInstance(MovieRemoteImp.getInstance(RetrofitHelper.service)
        , LocalDataMovieImp.getInstance(DatabaseClient.getInstance(requireContext()).movieApp())
        ))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoiriteAdapter=FavoiriteAdapter({movie->
            viewModel.getFavData()
            val action=FavouriteFragmentDirections.actionNavigationFavToDetails(movie)
            findNavController().navigate(action)
        },onDeleteClick)
     binding.favrecycled.apply {
         layoutManager= LinearLayoutManager(requireContext())
         adapter=favoiriteAdapter

     }

        getFavData()
        }




    private fun getFavData() {
        viewModel.getFavData()
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.favMovie.collect{state->
                when(state) {
                    is ResponsState.Loading -> {
                        Log.d("popular", "featchPopularMovies:$state ")
                    }

                    is ResponsState.Error -> {
                        Log.d("popular", "featchPopularMovies:${state.message} ")
                    }

                    is ResponsState.Success -> {
                        favoiriteAdapter.submitList(state.data)
                        Log.d("popular", "featchPopularMovies: ${state.data}")
                    }
                }}}}


    val onDeleteClick: (Result) -> Unit = { favoriteMovie ->
        // Create an AlertDialog for delete confirmation
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Favorite")
            .setMessage("Are you sure you want to delete this favorite weather item?")
            .setPositiveButton("Yes") { dialog, _ ->
                viewModel.deletMovie(favoriteMovie)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
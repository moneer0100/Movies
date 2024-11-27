package com.example.movieapp.ui.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.FragmentSearchBinding
import com.example.movieapp.model.db.DatabaseClient
import com.example.movieapp.model.db.LocalDataMovieImp
import com.example.movieapp.model.network.MovieRemoteImp
import com.example.movieapp.model.network.ResponsState
import com.example.movieapp.model.network.RetrofitHelper
import com.example.movieapp.model.pojo.RepoMovieImp
import com.example.movieapp.ui.search.viewModel.SearchViewFactory
import com.example.movieapp.ui.search.viewModel.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private lateinit var searchAdapter: SearchAdapter
    private val binding get() = _binding!!

    // ViewModel initialization
    private val searchViewModel: SearchViewModel by viewModels {
        SearchViewFactory(
            RepoMovieImp.getInstance(
                MovieRemoteImp.getInstance(RetrofitHelper.service),
                LocalDataMovieImp.getInstance(DatabaseClient.getInstance(requireContext()).movieApp())
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the adapter for RecyclerView
        searchAdapter = SearchAdapter { movie ->
            val action = SearchFragmentDirections.actionNavigationSearchToDetails(movie)
            findNavController().navigate(action)
        }

        // Set up the RecyclerView with GridLayoutManager
        binding.recyclerSearch.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = searchAdapter
        }

        // Listen to text changes in the EditText
        binding.editTextText.addTextChangedListener { text ->
            val query = text.toString().trim()
            if (query.isNotEmpty()) {
                // Pass the updated query to the ViewModel
                searchViewModel.getSearch(query)
            }
        }

        // Observe the search results from the ViewModel
        lifecycleScope.launch(Dispatchers.Main) {
            searchViewModel.search.collect { state ->
                when (state) {
                    is ResponsState.Loading -> {
                        // Optionally show a loading indicator
                    }
                    is ResponsState.Success -> {
                        // Update the adapter with the search results
                        searchAdapter.submitList(state.data.results)
                    }
                    is ResponsState.Error -> {
                        // Handle the error (show error message)
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

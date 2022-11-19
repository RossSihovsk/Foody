package com.example.foody.screen

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.R
import com.example.foody.adapters.FavoritesRecipesAdapter
import com.example.foody.databinding.FragmentFavoriteRecipesBinding
import com.example.foody.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter: FavoritesRecipesAdapter by lazy {
        FavoritesRecipesAdapter(
            requireActivity(),
            mainViewModel
        )
    }

    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_recipes, container, false)

        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mAdapter = mAdapter
        binding.mainViewModel = mainViewModel

        setHasOptionsMenu(true)

        setupRecycleView(binding.favoriteRecipesRecycleView)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorites_recipes_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.deleteAll_favorite_recipes_menu) {
            mainViewModel.deleteAllRecipes()
            showSnackBar("All recipes removed")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay") {}.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mAdapter.clearContextualActionMode()
    }

    private fun setupRecycleView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
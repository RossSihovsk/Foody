package com.example.foody.adapters

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.R
import com.example.foody.data.database.entities.FavoritesEntity
import com.example.foody.databinding.FavoriteRecipesRowLayoutBinding
import com.example.foody.screen.FavoriteRecipesFragmentDirections
import com.example.foody.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.favorite_recipes_row_layout.view.*

class FavoritesRecipesAdapter(
    private val requireActivity: FragmentActivity,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<FavoritesRecipesAdapter.MyViewHolder>(), ActionMode.Callback {

    private var favoritesRecipes = emptyList<FavoritesEntity>()
    private var selectedRecipes = arrayListOf<FavoritesEntity>()
    private var multiSelection = false
    private var myViewHolders = arrayListOf<MyViewHolder>()
    private lateinit var mActionMode: ActionMode
    private lateinit var rootView: View

    class MyViewHolder(private val binding: FavoriteRecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoritesEntity: FavoritesEntity) {
            binding.favoritesEntity = favoritesEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        myViewHolders.add(holder)
        rootView = holder.itemView.rootView

        val currentRecipe = favoritesRecipes[position]
        holder.bind(currentRecipe)

        /**
         * Single Click Listener
         */
        holder.itemView.favoriteRecipesRowLayout.setOnClickListener {
            if (multiSelection) {
                applySelection(holder, currentRecipe)
            } else {
                val action =
                    FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToDetailsActivity(
                        currentRecipe.result
                    )
                holder.itemView.findNavController().navigate(action)
            }
        }

        /**
         * Long Click Listener
         */
        holder.itemView.favoriteRecipesRowLayout.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                requireActivity.startActionMode(this)
                applySelection(holder, currentRecipe)
                true
            } else {
                multiSelection = false
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return favoritesRecipes.size
    }

    fun setData(newFavoritesRecipes: List<FavoritesEntity>) {
        val favoritesRecipesDiffUtil = RecipesDiffUtil(favoritesRecipes, newFavoritesRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoritesRecipesDiffUtil)
        favoritesRecipes = newFavoritesRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }

    private fun applyStatusBarColor(color: Int) {
        requireActivity.window.statusBarColor =
            ContextCompat.getColor(requireActivity, color)
    }

    private fun applySelection(holder: MyViewHolder, currentRecipe: FavoritesEntity) {
        if (selectedRecipes.contains(currentRecipe)) {
            selectedRecipes.remove(currentRecipe)
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
            applyApplyActionModeTitle()
        } else {
            selectedRecipes.add(currentRecipe)
            changeRecipeStyle(holder, R.color.cardBackgroundLightColor, R.color.colorPrimary)
            applyApplyActionModeTitle()
        }
    }

    fun clearContextualActionMode() {
        if (this::mActionMode.isInitialized) {
            mActionMode.finish()
        }
    }

    private fun changeRecipeStyle(holder: MyViewHolder, backgroundColor: Int, strokeColor: Int) {
        holder.itemView.favoriteRecipesRowLayout.setBackgroundColor(
            ContextCompat.getColor(
                requireActivity,
                backgroundColor
            )
        )

        holder.itemView.favorite_row_cardView.strokeColor = ContextCompat.getColor(
            requireActivity,
            strokeColor
        )
    }

    private fun applyApplyActionModeTitle() {
        when (selectedRecipes.size) {
            0 -> {
                mActionMode.finish()
            }
            1 -> {
                mActionMode.title = "${selectedRecipes.size} item selected"
            }
            else -> {
                mActionMode.title = "${selectedRecipes.size} items selected"
            }
        }
    }

    override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        actionMode?.menuInflater?.inflate(R.menu.favorites_contextual_menu, menu)
        mActionMode = actionMode!!
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menu: MenuItem?): Boolean {
        if (menu?.itemId == R.id.delete_favorites_recipe_menu) {
            selectedRecipes.forEach {
                mainViewModel.deleteFavoritesRecipe(it)
            }
            showSnackBar("${selectedRecipes.size} Recipe/s removed")
            multiSelection = false
            selectedRecipes.clear()
            actionMode?.finish()
        }

        return true
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).setAction("Okay") {}.show()
    }

    override fun onDestroyActionMode(actionMode: ActionMode) {
        multiSelection = false
        selectedRecipes.clear()
        myViewHolders.forEach { holder ->
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
        }
        applyStatusBarColor(R.color.statusBarColor)
    }
}
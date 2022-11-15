package com.example.foody.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.R
import com.example.foody.RECIPES_RESULT_KEY
import com.example.foody.adapters.IngredientsAdapter
import com.example.foody.models.Result
import kotlinx.android.synthetic.main.fragment_ingredients.view.*

class IngredientsFragment : Fragment() {

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingredients, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPES_RESULT_KEY)

        setupRecycleView(view)
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }

        return view
    }

    private fun setupRecycleView(view: View) {
        view.ingredients_recycleView.adapter = mAdapter
        view.ingredients_recycleView.layoutManager = LinearLayoutManager(requireContext())
    }
}
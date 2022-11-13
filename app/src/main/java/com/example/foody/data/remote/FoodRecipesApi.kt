package com.example.foody.data.remote

import com.example.foody.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries:Map<String,String>
    ): Response<FoodRecipe>

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap queries:Map<String,String>
    ): Response<FoodRecipe>
}
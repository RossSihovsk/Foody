package com.example.foody.data.database

import androidx.room.*
import com.example.foody.FAVORITE_RECIPES_TABLE
import com.example.foody.FOOD_JOKE_TABLE
import com.example.foody.RECIPES_TABLE
import com.example.foody.data.database.entities.FavoritesEntity
import com.example.foody.data.database.entities.FoodJokeEntity
import com.example.foody.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Query("SELECT * FROM $RECIPES_TABLE ORDER BY id ASC ")
    fun readRecipes(): Flow<List<RecipesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritesRecipes(favoriteEntity: FavoritesEntity)

    @Query("SELECT * FROM $FAVORITE_RECIPES_TABLE ORDER BY id ASC ")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoriteEntity: FavoritesEntity)

    @Query("DELETE FROM $FAVORITE_RECIPES_TABLE")
    suspend fun deleteAllFavoriteRecipes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity)

    @Query("SELECT * FROM $FOOD_JOKE_TABLE ORDER BY id ASC")
    fun readFoodJoke(): Flow<List<FoodJokeEntity>>
}
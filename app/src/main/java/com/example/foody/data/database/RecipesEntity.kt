package com.example.foody.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.RECIPES_TABLE
import com.example.foody.models.FoodRecipe

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}
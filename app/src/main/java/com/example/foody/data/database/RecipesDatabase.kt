package com.example.foody.data.database

import androidx.room.Database
import androidx.room.TypeConverters

@Database(
    entities = [RecipesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConvertor::class)
abstract class RecipesDatabase {

    abstract fun recipesDao(): RecipesDao
}
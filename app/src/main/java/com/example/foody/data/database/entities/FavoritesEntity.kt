package com.example.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.FAVORITE_RECIPES_TABLE
import com.example.foody.models.Result

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)
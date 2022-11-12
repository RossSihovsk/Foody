package com.example.foody

const val BASE_URL = "https://api.spoonacular.com"
const val  API_KEY = "354051a4cb8544ea9f0b777b7619655e"

// API Query Keys
const val QUERY_NUMBER = "number"
const val QUERY_API_KEY = "apiKey"
const val QUERY_TYPE = "main course"
const val QUERY_DIET = "gluten free"
const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
const val QUERY_FILL_INGREDIENTS = "fillIngredients"

//Room Database
const val DATABASE_NAME = "recipes_database"
const val RECIPES_TABLE = "recipes_table"

// Bottom Sheet and Preferences
const val DEFAULT_RECIPES_NUMBER = "50"
const val DEFAULT_MEAL_TYPE = "main course"
const val DEFAULT_DIET_TYPE = "gluten free"

const val PREFERENCES_NAME = "foody_preferences"
const val PREFERENCES_MEAL_TYPE = "mealType"
const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
const val PREFERENCES_DIET_TYPE = "dietType"
const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"
const val PREFERENCES_BACK_ONLINE = "backOnline"
package com.surajrathod.fudiz.network

import com.surajrathod.fudiz.model.Category
import com.surajrathod.fudiz.model.Food
import com.surajrathod.fudiz.model.Meals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("categories.php")
    fun getCategories() : Call<Category>


    @GET("filter.php?")
    fun getCatMeals(
        @Query("c") c : String
    ) : Call<Meals>


    @GET("lookup.php?")
    fun getMealById(
        @Query("i") i : String
    ) : Call<Food>
}
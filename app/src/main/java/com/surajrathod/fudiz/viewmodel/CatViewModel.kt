package com.surajrathod.fudiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surajrathod.fudiz.model.*
import com.surajrathod.fudiz.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatViewModel : ViewModel() {

    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .build().create(ApiInterface::class.java)

    private val _catList = MutableLiveData<MutableList<CategoryX>>(mutableListOf())
    val catList : LiveData<MutableList<CategoryX>>
        get() = _catList

    private val _mealList = MutableLiveData<MutableList<Meal>>(mutableListOf())
    val mealList : LiveData<MutableList<Meal>>
        get() = _mealList

    //lateinit var meal : MealX



    private fun clearPrograms() = _catList.value?.clear()

    private fun refresh(){
        _catList.value = _catList.value
    }

    fun getData()
    {
        retrofitBuilder.getCategories().enqueue(object : Callback<Category?> {
            override fun onResponse(call: Call<Category?>, response: Response<Category?>) {

                response.body().let {

                    clearPrograms()

                    _catList.value?.addAll(it!!.categories)

                    refresh()

                }

            }

            override fun onFailure(call: Call<Category?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getMealBy(c : String)
    {

        retrofitBuilder.getCatMeals(c).enqueue(object : Callback<Meals?> {
            override fun onResponse(call: Call<Meals?>, response: Response<Meals?>) {
                response.body().let {

                    _mealList.value?.clear()
                    _mealList.value?.addAll(it!!.meals)

                    _mealList.value = _mealList.value
                }
            }

            override fun onFailure(call: Call<Meals?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun setMealz(m : MealX)
    {


    }

    fun getMealById(id : String){

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .build().create(ApiInterface::class.java)

        retrofitBuilder.getMealById(id).enqueue(object : Callback<Food?> {
            override fun onResponse(call: Call<Food?>, response: Response<Food?>) {

                response.body().let {

                    setMealz(it?.meals!!.first())

                }

            }

            override fun onFailure(call: Call<Food?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


}
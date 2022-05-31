package com.surajrathod.fudiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.surajrathod.fudiz.R
import com.surajrathod.fudiz.adapter.CatAdapter
import com.surajrathod.fudiz.adapter.MealAdapter
import com.surajrathod.fudiz.model.Meals
import com.surajrathod.fudiz.network.ApiInterface
import com.surajrathod.fudiz.viewmodel.CatViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_meals.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsActivity : AppCompatActivity() {

    lateinit var adapter : MealAdapter
    lateinit var catViewModel : CatViewModel


    var c = "Seafood"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        catViewModel = ViewModelProvider(this).get(CatViewModel::class.java)

        var cat = intent.getStringExtra("id").toString()
        c = cat
        //getData()

        catViewModel.getMealBy(c)
        catViewModel.mealList.observe(this,{
            adapter.setDataList(it)
        })


        adapter = MealAdapter(this)
        rvMeals.layoutManager = GridLayoutManager(this,2)
        rvMeals.adapter = adapter
        rvMeals.hasFixedSize()



    }

    fun getData() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .build().create(ApiInterface::class.java)

        val data = retrofitBuilder.getCatMeals(c)

        data.enqueue(object : Callback<Meals?> {
            override fun onResponse(call: Call<Meals?>, response: Response<Meals?>) {
                val body = response.body()

                if(body == null){
                    Toast.makeText(this@MealsActivity,"failed", Toast.LENGTH_LONG).show()

                }

                adapter.setDataList(body?.meals!!)
            }

            override fun onFailure(call: Call<Meals?>, t: Throwable) {

            }
        })

    }


}
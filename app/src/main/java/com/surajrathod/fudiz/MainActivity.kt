package com.surajrathod.fudiz

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.surajrathod.fudiz.adapter.CatAdapter
import com.surajrathod.fudiz.adapter.MealAdapter
import com.surajrathod.fudiz.model.Category
import com.surajrathod.fudiz.model.CategoryX
import com.surajrathod.fudiz.model.Meal
import com.surajrathod.fudiz.model.Meals
import com.surajrathod.fudiz.network.ApiInterface
import com.surajrathod.fudiz.viewmodel.CatViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var adapter : CatAdapter
    lateinit var adapter1 : MealAdapter
    val list = listOf<Meal>(Meal("sd","sd","sd"))

    lateinit var catViewModel : CatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val color = ColorDrawable(Color.parseColor("#3e416e"))
        supportActionBar?.setBackgroundDrawable(color)
        progressBar.visibility = View.VISIBLE
        catViewModel = ViewModelProvider(this@MainActivity).get(CatViewModel::class.java)
        //getData()

        catViewModel.getData()
        catViewModel.catList.observe(this, Observer {
            adapter.setDataList(it)
        })

        adapter = CatAdapter(this)
        rvCategories.layoutManager = GridLayoutManager(this,3)
        rvCategories.adapter = adapter
        rvCategories.hasFixedSize()
        progressBar.visibility = View.GONE







//
//        getData1()
//
//        adapter1 = MealAdapter(this)
//        rvCategories.layoutManager = GridLayoutManager(this,3)
//        rvCategories.adapter = adapter1
//









    }




    fun getData()
    {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .build().create(ApiInterface::class.java)

        val data = retrofitBuilder.getCategories()

        data.enqueue(object : Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                val body = response.body()

                if(body == null){
                    Toast.makeText(this@MainActivity,"failed",Toast.LENGTH_LONG).show()

                }

                adapter.setDataList(body?.categories!!)

            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@MainActivity,"failed", Toast.LENGTH_LONG).show()
            }

        })

    }

    fun getData1() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .build().create(ApiInterface::class.java)

        val data = retrofitBuilder.getCatMeals("Seafood")

        data.enqueue(object : Callback<Meals?> {
            override fun onResponse(call: Call<Meals?>, response: Response<Meals?>) {
                val body = response.body()

                if(body == null){
                    Toast.makeText(this@MainActivity,"failed", Toast.LENGTH_LONG).show()

                }

                adapter1.setDataList(body?.meals!!)
            }

            override fun onFailure(call: Call<Meals?>, t: Throwable) {

            }
        })

    }
}
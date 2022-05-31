package com.surajrathod.fudiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.surajrathod.fudiz.model.Food
import com.surajrathod.fudiz.model.MealX
import com.surajrathod.fudiz.network.ApiInterface
import com.surajrathod.fudiz.viewmodel.CatViewModel
import kotlinx.android.synthetic.main.activity_food_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodInfoActivity : AppCompatActivity() {


    lateinit var catViewModel : CatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_info)
        val id = intent.getStringExtra("mId")





        getMealById(id!!)




        //Toast.makeText(this@FoodInfoActivity,"Object Succes ${meal.strArea}",Toast.LENGTH_SHORT).show()


        //txtTitle.text = "${meal.strMeal}"


    }

    fun setMeal(m : MealX) {
        txtCountry.text = m.strArea.toString()
        imgMeal.load(m.strMealThumb) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
        }

        txtTitle.text = m.strMeal.toString()

        txtCountryInfo.text = m.strArea
        txtCategoryInfo.text = m.strCategory
        txtInstruction.text = m.strInstructions

        //Something Insame



        if(!m.strIngredient1.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient1)
        }
        if(!m.strIngredient2.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient2)
        }
        if(!m.strIngredient3.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient3)
        }
        if(!m.strIngredient4.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient4)
        }
        if(!m.strIngredient5.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient5)
        }
        if(!m.strIngredient6.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient6)
        }
        if(!m.strIngredient7.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient7)
        }
        if(!m.strIngredient8.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient8)
        }
        if(!m.strIngredient9.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient9)
        }
        if(!m.strIngredient10.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient10)
        }
        if(!m.strIngredient11.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient11)
        }
        if(!m.strIngredient12.isNullOrEmpty()){
            txtIngredients.append("\n \u2022 " + m.strIngredient12)
        }
        if(!m.strIngredient13.isNullOrEmpty() && !Character.isWhitespace(m.strIngredient13.get(0))){
            txtIngredients.append("\n \u2022 " + m.strIngredient13)
        }

        if(!m.strIngredient14.isNullOrEmpty() && !Character.isWhitespace(m.strIngredient14.get(0))){
            txtIngredients.append("\n \u2022 " + m.strIngredient14)
        }
        if(!m.strIngredient15.isNullOrEmpty() && !Character.isWhitespace(m.strIngredient15.get(0))){
            txtIngredients.append("\n \u2022 " + m.strIngredient15)
        }
        if(!m.strIngredient16.isNullOrEmpty() && !Character.isWhitespace(m.strIngredient16.get(0))){
            txtIngredients.append("\n \u2022 " + m.strIngredient16)
        }
        if(!m.strIngredient17.isNullOrEmpty() && !Character.isWhitespace(m.strIngredient17.get(0))){
            txtIngredients.append("\n \u2022 " + m.strIngredient17)
        }
        if(!m.strIngredient18.isNullOrEmpty() && !Character.isWhitespace(m.strIngredient18.get(0))){
            txtIngredients.append("\n \u2022 " + m.strIngredient18)
        }
        if(!m.strIngredient19.isNullOrEmpty() && !Character.isWhitespace(m.strIngredient19.get(0))){
            txtIngredients.append("\n \u2022 " + m.strIngredient19)
        }
        if(!m.strIngredient20.isNullOrEmpty() && !Character.isWhitespace(m.strIngredient20.get(0))){
            txtIngredients.append("\n \u2022 " + m.strIngredient20)
        }

        //for measure

        if(!m.strMeasure1.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure1.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure1)
        }else{

        }
        if(!m.strMeasure2.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure2.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure2)
        }
        if(!m.strMeasure3.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure3.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure3)
        }
        if(!m.strMeasure4.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure4.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure4)
        }
        if(!m.strMeasure5.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure5.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure5)
        }
        if(!m.strMeasure6.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure6.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure6)
        }
        if(!m.strMeasure7.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure7.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure7)
        }
        if(!m.strMeasure8.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure8.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure8)
        }
        if(!m.strMeasure9.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure9.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure9)
        }
        if(!m.strMeasure10.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure10.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure10)
        }
        if(!m.strMeasure11.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure11.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure11)
        }
        if(!m.strMeasure12.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure12.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure12)
        }
        if(!m.strMeasure13.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure13.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure13)
        }
        if(!m.strMeasure14.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure14.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure14)
        }
        if(!m.strMeasure15.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure15.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure15)
        }
        if(!m.strMeasure16.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure16.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure16)
        }
        if(!m.strMeasure17.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure17.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure17)
        }
        if(!m.strMeasure18.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure18.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure18)
        }
        if(!m.strMeasure19.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure19.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure19)
        }
        if(!m.strMeasure20.isNullOrEmpty() && !Character.isWhitespace(m.strMeasure20.get(0))){
            txtMeasure.append("\n : "+ m.strMeasure20)
        }



    }

    fun getMealById(id : String){

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .build().create(ApiInterface::class.java)

        retrofitBuilder.getMealById(id).enqueue(object : Callback<Food?> {
            override fun onResponse(call: Call<Food?>, response: Response<Food?>) {

                response.body().let {

                    if (it != null) {

                        setMeal(it?.meals!!.first())
                    }



                }

            }

            override fun onFailure(call: Call<Food?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
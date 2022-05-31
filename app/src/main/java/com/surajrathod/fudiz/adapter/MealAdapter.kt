package com.surajrathod.fudiz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.surajrathod.fudiz.FoodInfoActivity
import com.surajrathod.fudiz.MainActivity
import com.surajrathod.fudiz.R
import com.surajrathod.fudiz.model.CategoryX
import com.surajrathod.fudiz.model.Meal
import com.surajrathod.fudiz.ui.MealInfoActivity

class MealAdapter(context : Context)  : RecyclerView.Adapter<MealAdapter.ViewHolder>(){

    var dataList = emptyList<Meal>()

    internal fun setDataList(dataList : List<Meal>){
        this.dataList = dataList
        notifyDataSetChanged()

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var catName : TextView
        val image : ImageView
        init {
            catName= itemView.findViewById(R.id.txtCatName)
            image = itemView.findViewById(R.id.catImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = dataList[position]

        holder.apply {
            catName.text = data.strMeal
            image.load(data.strMealThumb){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)

            }


            image.setOnClickListener {


                Toast.makeText(it.context,"clicked ${data.idMeal}",Toast.LENGTH_SHORT).show()
                var intent = Intent(it.context,FoodInfoActivity::class.java)
                intent.putExtra("mId",data.idMeal)

                it.context.startActivity(intent)

            }
        }




    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
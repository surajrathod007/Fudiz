package com.surajrathod.fudiz.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.surajrathod.fudiz.R
import com.surajrathod.fudiz.model.CategoryX
import com.surajrathod.fudiz.ui.MealInfoActivity
import com.surajrathod.fudiz.ui.MealsActivity

class CatAdapter(context : Context) :  RecyclerView.Adapter<CatAdapter.ViewHolder>(){

    var dataList = emptyList<CategoryX>()

    internal fun setDataList(dataList : List<CategoryX>){
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
            catName.text = data.strCategory
            image.load(data.strCategoryThumb){
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)

            }

            image.setOnClickListener {

                var intent = Intent(it.context,MealsActivity::class.java)
                intent.putExtra("id",data.strCategory)
                it.context.startActivity(intent)


            }
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
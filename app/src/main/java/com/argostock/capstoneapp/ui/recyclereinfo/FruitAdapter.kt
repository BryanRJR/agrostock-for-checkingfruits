package com.argostock.capstoneapp.ui.recyclereinfo


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.argostock.capstoneapp.R
import com.argostock.capstoneapp.databinding.ItemListBinding


class FruitAdapter (var c: Context, var fruitList:ArrayList<FruitData>):RecyclerView.Adapter<FruitAdapter.FruitViewHolder>()
{
    inner class FruitViewHolder(var v: ItemListBinding): RecyclerView.ViewHolder(v.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemListBinding>(
            inflter, R.layout.item_list,parent,
            false)
        return FruitViewHolder(v)

    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val newList = fruitList[position]
        holder.v.isFruits = fruitList[position]
        holder.v.root.setOnClickListener {
            val img = newList.img
            val name = newList.name
            val info = newList.info

            /**set Data*/
            val mIntent = Intent(c, DetailActivity::class.java)
            mIntent.putExtra("img",img)
            mIntent.putExtra("name",name)
            mIntent.putExtra("info",info)
            c.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return  fruitList.size
    }
}
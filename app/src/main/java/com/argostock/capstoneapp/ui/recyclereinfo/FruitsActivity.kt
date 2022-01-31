package com.argostock.capstoneapp.ui.recyclereinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.argostock.capstoneapp.R
import com.argostock.capstoneapp.databinding.ActivityFruitsBinding
import com.argostock.capstoneapp.databinding.ActivityNewsBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_fruits.*

class FruitsActivity : AppCompatActivity() {

    lateinit var mDataBase:DatabaseReference
    private lateinit var fruitList:ArrayList<FruitData>
    private lateinit var mAdapter: FruitAdapter
    private lateinit var fruitBinding: ActivityFruitsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fruitBinding = ActivityFruitsBinding.inflate(layoutInflater)
        setContentView(fruitBinding.root)
        fruitBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
        /**initialized*/
        fruitList = ArrayList()
        mAdapter = FruitAdapter(this,fruitList)
        recyclerAnimals.layoutManager = LinearLayoutManager(this)
        recyclerAnimals.setHasFixedSize(true)
        // recyclerAnimals.adapter = mAdapter
        /**getData firebase*/
        getAnimalsData()

    }
    /**ok now create new activity*/


    private fun getAnimalsData() {

        mDataBase = FirebaseDatabase.getInstance().getReference("Fruits")
        mDataBase.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (fruitSnapshot in snapshot.children){
                        val animal = fruitSnapshot.getValue(FruitData::class.java)
                        fruitList.add(animal!!)
                    }
                    recyclerAnimals.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@FruitsActivity,
                    error.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}
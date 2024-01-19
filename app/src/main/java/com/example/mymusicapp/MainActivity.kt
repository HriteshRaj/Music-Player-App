package com.example.mymusicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
lateinit var recyclerView: RecyclerView
lateinit var myadapter:MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.rview)
    val retrofitBuilder=Retrofit.Builder()
        .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

           val retrofitdata=retrofitBuilder.getData("eminem")

        retrofitdata.enqueue(object : Callback<MyDataClass?> {
            override fun onResponse(
                call: Call<MyDataClass?>,
                response: Response<MyDataClass?>



            ) {
                val Datalist= response.body()?.data!!
                myadapter= MyAdapter(this@MainActivity,Datalist)
                recyclerView.adapter=myadapter
                recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)

                myadapter.setOnItemClickListener(object: MyAdapter.OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intent= Intent(this@MainActivity,MusicPlayActivity::class.java)
                        intent.putExtra("MusicCover",Datalist[position].album.cover)
                        intent.putExtra("MusicTitle",Datalist[position].album.title)
                        intent.putExtra("Music",Datalist[position].preview)



                        startActivity(intent)
                    }
                })








            }

            override fun onFailure(call: Call<MyDataClass?>, t: Throwable) {
                Log.d("On failure","Main activy"+t.message)

            }
        })

    }
}
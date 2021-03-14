package com.example.recyclerviewretrofit

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewretrofit.news.Article
import com.example.recyclerviewretrofit.news.Welcome
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RetrofitRecyclerAdapter.SetOnClickListner {

    lateinit var recyclerView: RecyclerView
    private val apiKey = "e51b881b174644c7b2dea10093a5e63d"
    private var category = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topTextView: TextView = findViewById(R.id.main_top_textView)
        val typeFace: Typeface? = ResourcesCompat.getFont(this, R.font.lobster_regular)
        topTextView.typeface = typeFace

        recyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mainRetrofit(category)

        //headline button click listener
        headlineBtn.setOnClickListener {
            headlineBtn.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.start()
            headlineBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.black))
            technologyBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            businessBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            sportsBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            headlineBtn.setTextColor(ContextCompat.getColor(this,R.color.purple_700))
            technologyBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            businessBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            sportsBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            category = ""
            mainRetrofit(category)
        }

        //technology button click listener
        technologyBtn.setOnClickListener {
            technologyBtn.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.start()
            technologyBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.black))
            headlineBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            businessBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            sportsBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            technologyBtn.setTextColor(ContextCompat.getColor(this,R.color.purple_700))
            headlineBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            businessBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            sportsBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            category = "technology"
            mainRetrofit(category)
        }

        //business button click listener
        businessBtn.setOnClickListener {
            businessBtn.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.start()
            businessBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.black))
            headlineBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            technologyBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            sportsBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            businessBtn.setTextColor(ContextCompat.getColor(this,R.color.purple_700))
            technologyBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            headlineBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            sportsBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            category = "business"
            mainRetrofit(category)
        }

        //sports button click listener
        sportsBtn.setOnClickListener {
            sportsBtn.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.start()
            sportsBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.black))
            headlineBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            technologyBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            businessBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_700))
            sportsBtn.setTextColor(ContextCompat.getColor(this,R.color.purple_700))
            technologyBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            headlineBtn.setTextColor(ContextCompat.getColor(this,R.color.white))
            businessBtn.setTextColor(ContextCompat.getColor(this,R.color.white))

            category = "sports"
            mainRetrofit(category)
        }
    }

    private fun mainRetrofit(category: String){

        progressBar.visibility = View.VISIBLE
        val api = NetworkService()
        api.getNews(apiKey,category).enqueue(object : Callback<Welcome> {
            override fun onResponse(call: Call<Welcome>, response: Response<Welcome>) {
                val dataResponse = response.body()
                recyclerView.adapter = RetrofitRecyclerAdapter(dataResponse!!.articles as ArrayList<Article>, this@MainActivity)
                progressBar.visibility = View.GONE
            }
            override fun onFailure(call: Call<Welcome>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Your internet is off", Toast.LENGTH_LONG).show()
                println("ErrorStart")
                onFailure(call,t)
                println("ErrorEnd")
                progressBar.visibility = View.GONE
            }
        })
    }

    // Handling click of recyclerview
    override fun onItemCellClick(item: Article) {

        val builder = CustomTabsIntent.Builder()
        val intent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(this,R.color.purple_700))

        intent.launchUrl(this, Uri.parse(item.url))
    }
}
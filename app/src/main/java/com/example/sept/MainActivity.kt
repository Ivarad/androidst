package com.example.sept

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var items: ArrayList<FlexItem> = ArrayList()
    lateinit var news : News
    var count = -1;

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.run{
           putCharSequenceArrayList("Array", items as java.util.ArrayList<CharSequence>?)
            putInt("Key", count)
       }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt("Key");
        items = savedInstanceState.getCharSequenceArrayList("Array") as ArrayList<FlexItem>
        putpup()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())

        news =
            News("http://newsapi.org/v2/top-headlines?country=us&apiKey=bc62f7ad76fb4ac6b5f4542b18ad8507&q=")

        for (count in 1..news.getCountNews()) {
            items.add(FlexItem(news.getContentNews(count - 1),news.getImage(count-1)))
        }
        val recyclerView = findViewById<RecyclerView>(R.id.fleximgrecv)
        fleximgrecv.layoutManager = LinearLayoutManager(this)
        fleximgrecv.setHasFixedSize(true)
        fleximgrecv.adapter = RecyclerAdapter(this, items){
            val intent : Intent = Intent(this, Flex::class.java)
            intent.putExtra("OBJECT", it)
            startActivity(intent)
        }
    }

    fun onclick(view: View){
        if(country.text.toString() != ""){
            putpup();
        }
        else
        {
            putpup()
        }

    }
    fun putpup()
    {
        fleximgrecv.recycledViewPool.clear();
        items.clear();

        news.changeflex("us", search.text.toString())

        for (count in 1..news.getCountNews()) {
            items.add(FlexItem(news.getContentNews(count - 1), news.getImage(count - 1)))
        }

        fleximgrecv.layoutManager = LinearLayoutManager(this)
        fleximgrecv.setHasFixedSize(true)
        fleximgrecv.adapter = RecyclerAdapter(this, items){
            val intent : Intent = Intent(this, Flex::class.java)
            intent.putExtra("OBJECT", it)
            startActivity(intent)
        }


    }
}


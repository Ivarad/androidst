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

//    override fun onSaveInstanceState(outState: Bundle) {
//       outState?.run{
//           putCharSequenceArray("Array", items as java.util.ArrayList<FlexItem>)
//       }
//    }
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

       /*if(country.text.toString() != "" && search.text.toString() != "")
        {
            putpup();
        }*/


    }

   /* private String getScreenOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            putpup();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){

    }*/

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

        //Log.d("Debug", "Тут был вася!")

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
   /* val URL = "http://newsapi.org/v2/top-headlines?country=us&apiKey=bc62f7ad76fb4ac6b5f4542b18ad8507"
    var okHttpClient: OkHttpClient = OkHttpClient()
    var news : String = "";*/


   /* fun get_author() : String
    {
        while(news == ""){}
        return news
    }*/

    //override fun onSaveInstanceState(outState: Bundle) {
        //super.onSaveInstanceState(outState)
        //outState.putInt("count", Countf.text)
  //  }

    /*override fun onRestoreInstanceState(savedInstanceState: Bundle, outState: Bundle) {
        //super.onRestoreInstanceState(savedInstanceState, persistentSate)
        //count = savedInstanceState!!.getInt("count")
        //Countf.text = count.toString()
    }
    fun flexclic(view: View)
    {
        var count = 0;
        Countf.text = (count++).toString()
    }*/

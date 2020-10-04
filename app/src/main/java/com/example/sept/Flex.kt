package com.example.sept

import okhttp3.*
import org.json.*
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONObject


class Flex : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val item : FlexItem? = intent.getParcelableExtra<FlexItem>("OBJECT")

        Picasso.get().load(item?.urlimg).fit().placeholder(R.drawable.black).error(R.drawable.black).into(_imageDetail)

        _discrip.text = "Заголовок: " + item?.content

    }
}


/*class TestUrl(){
    private var news : String = ""
    init {

        var okHttpClient: OkHttpClient = OkHttpClient()
        val request: Request = Request.Builder().url(url).build()
        val response : Response = okHttpClient.newCall(request).execute()

        if(response.isSuccessful){
            news = (JSONObject(response!!.body()!!.string()).getJSONArray("articles").getJSONObject(0).get("author").toString())
        }
        else
        {
            news = "Ошибочка!"
        }

    }
    fun get_author() : String
    {
        return news
    }*/

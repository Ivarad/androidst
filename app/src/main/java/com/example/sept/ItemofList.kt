package com.example.sept

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class News(url : String) {

    private var listContents : MutableList<String> = mutableListOf()
    private var listImage : MutableList<String> = mutableListOf()
    private var countNews : Int = 0
    var changenews : String = url
    init{
        changeflex("us", "*");
    }

    fun getCountNews() : Int {
        return countNews
    }

    fun getListContentNews() : MutableList<String> {
        return listContents
    }
    fun getListImage() : MutableList<String> {
        return listImage
    }

    fun getContentNews(index : Int) : String{
        return listContents.get(index)
    }

    fun getImage(index: Int) : String{
        return listImage.get(index)
    }

    fun clearLists()
    {
        listContents.clear()
        listImage.clear()
    }

    fun changeflex(Flexcountry: String, Flexq: String){

        clearLists()
        countNews = 0

        val httpClient : OkHttpClient = OkHttpClient()
        val request : Request = Request.Builder().url(changenews.replace("country=us", "country=$Flexcountry").replace("q=", "q=$Flexq")).build()
        //хм..
        val response: Response = httpClient.newCall(request).execute()
        var json : String = ""

        if (response.isSuccessful){
            json = response.body!!.string()
        }
        else{
            json = "{'Новость':[{'description':null}] }"
        }

        val count : Int = (JSONObject(json).getJSONArray("articles").length())

        if(json != "Error"){


            for(count2 in 1..count)
            {

                listContents.add((JSONObject(json).getJSONArray("articles").getJSONObject(count2 - 1)).get("description").toString())
                listImage.add((JSONObject(json).getJSONArray("articles").getJSONObject(count2-1)).get("urlToImage").toString())
            }

            countNews = count

            /*Log.d("Debug", countNews.toString())
            Log.d("Debug", changenews.replace("country=us", "country=$Flexcountry").replace("q=", "q=$Flexq"))
            Log.d("Debug", Flexq)*/
        }
        else
        {
            listContents.add("Error")
            listImage.add("Error!")
            countNews = 1
        }

    }

}
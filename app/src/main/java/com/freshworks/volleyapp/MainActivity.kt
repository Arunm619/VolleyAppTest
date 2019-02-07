package com.freshworks.volleyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val link = "https://api.github.com/repositories?since=100"
    var VolleyRequest: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VolleyRequest = Volley.newRequestQueue(this)

        getJsonString(link)
        getJsonArray("https://api.github.com/repositories?since=100")
        getJsonObject("https://api.github.com/")
    }

    //get String
    fun getJsonString(url: String) {
        val strRequest = StringRequest(Request.Method.GET, url,

            Response.Listener { response: String? ->
                try {

                    Log.d("Response", response)


                } catch (e: JSONException) {

                    Log.d("Response", "Error occured")
                    e.printStackTrace()

                }
            }
            ,

            Response.ErrorListener { error: VolleyError? ->


                try {
                    Log.d("Error", error.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

        )

        VolleyRequest?.add(strRequest)
    }

    //get Array of objects
    fun getJsonArray(url: String) {

        val jsonArray = JsonArrayRequest(Request.Method.GET, url,
            Response.Listener {

                    response: JSONArray ->
                try {
                    Log.d("Response", response.toString())
                    for (i in 0..response.length().minus(1)) {
                        val gitObj = response.getJSONObject(i)
                        val name = gitObj.getString("name")
                        Log.d("Name $i", name)


                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error: VolleyError? ->


                try {
                    Log.d("Error", error.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

        )

        VolleyRequest?.add(jsonArray)
    }

    //get JsonObject
    fun getJsonObject(url: String) {
        var jsonObj = JsonObjectRequest(Request.Method.GET, url,

            Response.Listener { response: JSONObject ->
                try {

                    Log.d("RESPONSEOBJECT", response.toString())


                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            },

            Response.ErrorListener { error: VolleyError ->
                try {
                    Log.d("Error", error.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            })

        VolleyRequest?.add(jsonObj)
    }


}


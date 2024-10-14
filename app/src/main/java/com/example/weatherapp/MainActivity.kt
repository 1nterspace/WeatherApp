package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import org.json.JSONObject

const val API_KEY = "ac0307daab714221979233808241210"
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bt.setOnClickListener {
            getResult("Moscow")
        }
    }

    private fun getResult(name:String){
        val url = "https://api.weatherapi.com/v1/current.json?" +
                "key=$API_KEY&q=$name&aqi=no\n"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET,
            url,
            {
                response->
                val obj = JSONObject(response)
                val current = obj.getJSONObject("current")
                val temp = current.getString("temp_c")

                Log.d("MyLog","Response: $temp")
            },
            {
                Log.d("MyLog","Volley Error: $it")
            }
            )
        queue.add(stringRequest)
    }

}
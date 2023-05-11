package com.orlov.android.orlovlab7

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.receiveDateButton).setOnClickListener {
            GlobalScope.launch(Dispatchers.Default) {
                val retrofit = Retrofit
                    .Builder()
                    .apply {
                        baseUrl(
                            "http://worldtimeapi.org/api/ip/"
                        )
                        addConverterFactory(
                            GsonConverterFactory.create()
                        )
                    }
                    .build()
                val api = retrofit.create(DateApi::class.java)
                try {
                    val result = api.getDateInfo(
                        withContext(Dispatchers.Main) { findViewById<EditText>(R.id.editText).text.toString() }
                    )
                    withContext(Dispatchers.Main) {
                        findViewById<TextView>(R.id.dateTimeView).text = result.datetime
                        findViewById<TextView>(R.id.timeZoneView).text = result.timezone
                        findViewById<TextView>(R.id.yearDayView).text = result.dayOfYear.toString()
                    }
                } catch (e: Exception) {
                    Log.d("TAG", "Api timeout")
                }
            }
        }
    }
}
//http://worldtimeapi.org/api/ip/2.16.14.255

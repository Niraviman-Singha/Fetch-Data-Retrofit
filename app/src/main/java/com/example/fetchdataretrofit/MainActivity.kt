package com.example.fetchdataretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var courseRV: RecyclerView
    lateinit var loadingPB: ProgressBar
    lateinit var adapter: CourseAdapter
    lateinit var courseList: ArrayList<CourseModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        courseRV = findViewById(R.id.idRVCourses)
        loadingPB = findViewById(R.id.idPBLoading)

        // on below line we are initializing our list
        courseList = ArrayList()

        // on below line we are calling
        // get all courses method to get data.
        getAllCourses()
    }

    private fun getAllCourses() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonkeeper.com/b/")
            // on below line we are calling add
            // Converter factory as Gson converter factory.
            // at last we are building our retrofit builder.
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // below line is to create an instance for our retrofit api class.
        val retrofitAPI = retrofit.create(RetrofitApi::class.java)

        // on below line we are calling a method to get all the courses from API.
        val call: Call<ArrayList<CourseModel>?>? = retrofitAPI.getAllCourses()

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call!!.enqueue(object : Callback<ArrayList<CourseModel>?> {
            override fun onResponse(
                call: Call<ArrayList<CourseModel>?>,
                response: Response<ArrayList<CourseModel>?>
            ) {
                if (response.isSuccessful) {
                    loadingPB.visibility = View.GONE
                    courseList = response.body()!!
                }

                // on below line we are initializing our adapter.
                adapter = CourseAdapter(courseList)

                // on below line we are setting adapter to recycler view.
                courseRV.adapter = adapter

            }

            override fun onFailure(call: Call<ArrayList<CourseModel>?>, t: Throwable) {
                // displaying an error message in toast
                Toast.makeText(this@MainActivity, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


}
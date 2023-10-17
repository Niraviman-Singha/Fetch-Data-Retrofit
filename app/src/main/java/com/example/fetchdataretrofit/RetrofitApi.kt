package com.example.fetchdataretrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("0RH6")
    fun getAllCourses(): Call<ArrayList<CourseModel>?>?
}
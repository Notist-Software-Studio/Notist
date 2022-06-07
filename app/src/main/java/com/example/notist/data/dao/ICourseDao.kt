package com.example.notist.data.dao

import com.example.notist.data.dto.Course
import retrofit2.Call
import retrofit2.http.GET
//list of methods from endpoints
interface ICourseDao {
    @GET("courses")
    fun getAllCourses() : Call<ArrayList<Course>>

}
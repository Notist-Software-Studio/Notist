package com.example.notist.data.service

import com.example.notist.data.RetrofitClientInstance
import com.example.notist.data.dao.ICourseDao
import com.example.notist.data.dto.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

interface ICourseService {
    suspend fun fetchCourses() : List<Course>?
}

class CourseService : ICourseService {
    override suspend fun fetchCourses() : List<Course>? {
        return withContext(Dispatchers.IO){
            val service = RetrofitClientInstance.retrofitInstance?.create(ICourseDao::class.java)
            //connect to server
            val courses = async {service?.getAllCourses()}
            val result = courses.await()?.awaitResponse()?.body()
            //receive the data ArrayList<Course>
            //we use async and await to wait the data load
            return@withContext result
        }
    }
}
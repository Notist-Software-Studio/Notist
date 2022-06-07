package com.example.notist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notist.data.dto.Course
import com.example.notist.data.service.CourseService
import kotlinx.coroutines.launch

class MainViewModel(var courseService: CourseService = CourseService()) : ViewModel() {
    var courses : MutableLiveData<List<Course>> = MutableLiveData<List<Course>>()
    fun fetchCourses(){
        viewModelScope.launch {
            var innerCourses = courseService.fetchCourses()
            courses.postValue((innerCourses))
        }
    }
}
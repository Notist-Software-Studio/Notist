package com.example.notist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notist.data.dto.Course
import com.example.notist.data.service.CourseService
import com.example.notist.data.service.ICourseService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

class MainViewModel(var courseService: ICourseService = CourseService()) : ViewModel() {
    var courses: MutableLiveData<List<Course>> = MutableLiveData<List<Course>>()
    private lateinit var firestore: FirebaseFirestore
    fun fetchCourses() {
        viewModelScope.launch {
            var innerCourses = courseService.fetchCourses()
            courses.postValue((innerCourses))
        }
    }

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun save(addCourse: Course) {
        val document = if (addCourse.courseId == null || addCourse.courseId.isEmpty()) {
            firestore.collection("courses").document()
        } else {
            firestore.collection("courses").document(addCourse.courseId)
        }
        addCourse.courseId = document.id
        var handle = document.set(addCourse)
        handle.addOnSuccessListener { Log.d("Firebase", "Document Saved") }
        handle.addOnFailureListener { Log.e("Firebase", "Save failed $it") }
    }
}
class UserViewModel() {
    var hunger: MutableState<Int> = mutableStateOf(5)
}
package com.example.notist

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notist.data.dto.Course
import com.example.notist.data.dto.Pdf
import com.example.notist.data.dto.User
import com.example.notist.data.service.CourseService
import com.example.notist.data.service.ICourseService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch

class MainViewModel(var courseService: ICourseService = CourseService()) : ViewModel() {
    val pdfs: ArrayList<Pdf> by mutableStateOf(ArrayList<Pdf>())
    var courses: MutableLiveData<List<Course>> = MutableLiveData<List<Course>>()
    private lateinit var firestore: FirebaseFirestore
    private var storageReference = FirebaseStorage.getInstance().getReference()
    var selectedCourseId by mutableStateOf(String())

    fun fetchCourses() {
        viewModelScope.launch {
            var courseCollection = firestore.collection("courses")
            var courseListener =
                courseCollection.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    querySnapshot?.let { querySnapshot ->
                        val coursetemp: ArrayList<Course> = ArrayList<Course>()
                        var documents = querySnapshot.documents
                        coursetemp.clear()
                        documents?.forEach {
                            var course = it.toObject(Course::class.java)
                            course?.let {
                                coursetemp.add(it)
                            }
                            courses.value = coursetemp

                        }
                    }
                }
        }

    }

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }
//    fun listenToCourses(){
//        firestore.collection("courses").addSnapshotListener()
//    }

    fun save(addCourse: Course) {
        val document = if (addCourse.courseId == null || addCourse.courseId.isEmpty()) {
            firestore.collection("courses").document()
        } else {
            firestore.collection("courses").document(addCourse.courseId)
        }
        addCourse.courseId = document.id
        var handle = document.set(addCourse)
        handle.addOnSuccessListener {
            Log.d("Firebase", "Document Saved")
            courses.value = courses.value

        }
        handle.addOnFailureListener { Log.e("Firebase", "Save failed $it") }
    }

    fun uploadPDF() {
        pdfs.forEach { pdf ->
            var uri = Uri.parse(pdf.localUri)
            val pdfRef = storageReference.child("pdfs/$selectedCourseId/${pdf.filename}")
            val uploadTask = pdfRef.putFile(uri)
            uploadTask.addOnSuccessListener {
                Log.i(ContentValues.TAG, "Pdf Uploaded $pdfRef")
                val downloadUrl = pdfRef.downloadUrl
                downloadUrl.addOnSuccessListener { remoteUri ->
                    pdf.remoteUri = remoteUri.toString()
                    updatePDFDatabase(pdf)

                }
            }
            uploadTask.addOnFailureListener {
                Log.e(ContentValues.TAG, it.message ?: "No message")
            }
        }
    }

    private fun updatePDFDatabase(pdf: Pdf) {
        var pdfDocument = if (pdf.id.isEmpty()) {
            firestore.collection("courses").document(selectedCourseId).collection("pdfs").document()
        } else {
            firestore.collection("courses").document(selectedCourseId).collection("pdfs")
                .document(pdf.id)
        }
        pdf.id = pdfDocument.id
        var handle = pdfDocument.set(pdf)
        handle.addOnSuccessListener {
            Log.i(ContentValues.TAG, "Successfully updated pdf metadata")
        }
        handle.addOnFailureListener {
            Log.e(ContentValues.TAG, "Error updating pdf data: ${it.message}")
        }
    }

    fun fetchPdfs() {
        pdfs.clear()
        var pdfCollection =
            firestore.collection("courses").document(selectedCourseId).collection("pdfs")
        var pdfsListener =
            pdfCollection.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                querySnapshot?.let { querySnapshot ->
                    val inPdfs = ArrayList<Pdf>()
                    var documents = querySnapshot.documents
                    documents?.forEach{
                        var pdf = it.toObject(Pdf::class.java)
                        pdf?.let{
                            inPdfs.add(it)
                        }
                    }
                }

            }
    }
}
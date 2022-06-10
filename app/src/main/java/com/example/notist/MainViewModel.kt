package com.example.notist

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notist.data.dto.Course
import com.example.notist.data.service.CourseService
import com.example.notist.data.service.ICourseService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.pspdfkit.document.PdfDocument
import com.pspdfkit.document.PdfDocumentLoader
import com.pspdfkit.document.download.DownloadJob
import com.pspdfkit.document.download.DownloadRequest
import com.pspdfkit.document.download.source.AssetDownloadSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

class PDFMainViewModel(application: Application) : AndroidViewModel(application) {

    // The list of PDFs in our assets folder
    private val assetsToLoad = listOf(
        "dummy.pdf",
        "sample.pdf"
    )

    private val mutableState = MutableStateFlow(State())
    val state: StateFlow<State> = mutableState

    fun loadPdfs() = viewModelScope.launch(Dispatchers.IO) {

        // Indicate that we are now loading
        mutableState.mutate { copy(loading = true) }

        val context = getApplication<Application>().applicationContext

        val pdfDocuments = assetsToLoad
            .map { extractPdf(context, it) }
            .map { it.toUri() to loadPdf(context, it.toUri()) }
            .toMap()

        // Stop loading and add the PDFs to our state
        mutableState.mutate {
            copy(
                loading = false,
                documents = pdfDocuments
            )
        }
    }

    fun openDocument(uri: Uri) {
        mutableState.mutate {
            copy(selectedDocumentUri = uri)
        }
    }

    fun closeDocument() {
        mutableState.mutate {
            copy(selectedDocumentUri = null)
        }
    }

    @SuppressLint("CheckResult")
    private suspend fun loadPdf(context: Context, uri: Uri) = suspendCoroutine<PdfDocument> { continuation ->
        PdfDocumentLoader
            .openDocumentAsync(context, uri)
            .subscribe(continuation::resume, continuation::resumeWithException)
    }

    private suspend fun extractPdf(context: Context, assetPath: String) = suspendCoroutine<File> { continuation ->
        val outputFile = File(context.filesDir, assetPath)
        val request = DownloadRequest.Builder(context)
            .source(AssetDownloadSource(context, assetPath))
            .outputFile(outputFile)
            .overwriteExisting(true)
            .build()

        val job = DownloadJob.startDownload(request)
        job.setProgressListener(
            object : DownloadJob.ProgressListenerAdapter() {
                override fun onComplete(output: File) {
                    continuation.resume(output)
                }

                override fun onError(exception: Throwable) {
                    super.onError(exception)
                    continuation.resumeWithException(exception)
                }
            }
        )
    }

    private fun <T> MutableStateFlow<T>.mutate(mutateFn: T.() -> T) {
        value = value.mutateFn()
    }
}
package com.example.notist

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
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
import com.example.notist.data.dto.State
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
import com.google.firebase.storage.FirebaseStorage

import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

// For opening PDF
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
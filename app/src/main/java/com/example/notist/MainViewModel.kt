package com.example.notist

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notist.data.dto.Course
import com.example.notist.data.dto.Pdf
import com.example.notist.data.dto.State
import com.example.notist.data.service.CourseService
import com.example.notist.data.service.ICourseService
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.storage.FirebaseStorage
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.document.PdfDocument
import com.pspdfkit.document.PdfDocumentLoader
import com.pspdfkit.document.download.DownloadJob
import com.pspdfkit.document.download.DownloadRequest
import com.pspdfkit.document.download.source.AssetDownloadSource
import com.pspdfkit.document.download.source.DownloadSource
import com.pspdfkit.ui.PdfActivityIntentBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


//import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(var courseService: ICourseService = CourseService()) : ViewModel() {
    private val mutableState = MutableStateFlow(State())
    val state = mutableState.asStateFlow()
    val pdfs: ArrayList<Pdf> by mutableStateOf(ArrayList<Pdf>())
    val downloadpdfs: MutableLiveData<List<Pdf>> = MutableLiveData<List<Pdf>>()
    var courses: MutableLiveData<List<Course>> = MutableLiveData<List<Course>>()
    var courses_saved: MutableLiveData<List<Course>> = MutableLiveData<List<Course>>()
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
    fun fetchCoursesSaved() {
        viewModelScope.launch {
            var courseCollection = firestore.collection("courses_saved")
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
                            courses_saved.value = coursetemp

                        }
                    }
                }
        }

    }
    fun moveFirestoreDocument(fromPath: DocumentReference, toPath: DocumentReference) {
        fromPath.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    toPath.set(document.data!!)
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot successfully written!")
                            fromPath.delete()
                                .addOnSuccessListener {
                                    Log.d(
                                        TAG,
                                        "DocumentSnapshot successfully deleted!"
                                    )
                                }
                                .addOnFailureListener { e ->
                                    Log.w(
                                        TAG,
                                        "Error deleting document",
                                        e
                                    )
                                }
                        }
                        .addOnFailureListener { e ->
                            Log.w(
                                TAG,
                                "Error writing document",
                                e
                            )
                        }
                } else {
                    Log.d(TAG, "No such document")
                }
            } else {
                Log.d(TAG, "get failed with ", task.exception)
            }
        }
    }
    fun saveCourseMyLibrary(addCourse: Course) {
        var currentdirectory = firestore.collection("courses").document(selectedCourseId)
        val document = firestore.collection("courses_saved").document(selectedCourseId)
        var currentdirectory2 = firestore.collection("courses").document(selectedCourseId).collection("pdfs")
        val document2 = firestore.collection("courses_saved").document(selectedCourseId).collection("pdfs")
        moveFirestoreDocument(currentdirectory,document)
//        moveFirestoreDocument(currentdirectory2,document2)

//        var handle = document.set(addCourse)
//        handle.addOnSuccessListener {
//            Log.d("Firebase", "Document Saved")
//            courses_saved.value = courses_saved.value
//
//        }
//        handle.addOnFailureListener { Log.e("Firebase", "Save failed $it") }
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
                    documents?.forEach {
                        var pdf = it.toObject(Pdf::class.java)
                        pdf?.let {
                            inPdfs.add(it)
                        }
                        downloadpdfs.value = inPdfs
                    }
                }
            }
    }
    fun downloadPDF(context: Context,urlname: String,filename:String){
        val source: WebDownloadSource = try {
            // Try to parse the URL pointing to the PDF document. If an error occurs, log it and leave the example.
            WebDownloadSource(URL("$urlname"))
        } catch (e: MalformedURLException) {
            Log.e(LOG_TAG, "Error while trying to parse the PDF Download URL.", e)
            return
        }

        // Build a download request based on various input parameters. Provide the web source pointing to the document.
        val request = DownloadRequest.Builder(context)
            .source(source)
            .outputFile(File(context.getDir("documents", Context.MODE_PRIVATE), "$filename" ))
            .overwriteExisting(true)
            .build()

        // This will initiate the download.
        val job = DownloadJob.startDownload(request)
        job.setProgressListener(object : DownloadJob.ProgressListenerAdapter() {
            override fun onComplete(output: File) {
                val intent = PdfActivityIntentBuilder.fromUri(context, Uri.fromFile(output))
                    .build()
                context.startActivity(intent)
            }

            override fun onError(exception: Throwable) {
                AlertDialog.Builder(context)
                    .setMessage("There was an error downloading the example PDF file. For further information see Logcat.")
                    .show()
            }
        })
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
                downloadpdfs.value = downloadpdfs.value
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

    val imageList = mutableStateListOf<ImageBitmap?>(null)


}
class PDFMainViewModel(application: Application) : AndroidViewModel(application) {

    // The list of PDFs in our assets folder
    private val assetsToLoad = listOf(
        "Annotations.pdf"
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
    private suspend fun loadPdf(context: Context, uri: Uri) =
        suspendCoroutine<PdfDocument> { continuation ->
            PdfDocumentLoader
                .openDocumentAsync(context, uri)
                .subscribe(continuation::resume, continuation::resumeWithException)
        }

    private suspend fun extractPdf(context: Context, assetPath: String) =
        suspendCoroutine<File> { continuation ->
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

class UserViewModel() {
    var hunger: MutableState<Int> = mutableStateOf(5)
}


class DocumentDownloadExample(context: Context) : PSPDFExample(context, R.string.openPdf, R.string.pdfList) {

    override fun launchExample(context: Context, configuration: PdfActivityConfiguration.Builder) {
        // The web download source is a custom DownloadSource implemented below.


//        val fragment = DownloadProgressFragment()
//        fragment.show((context as FragmentActivity).supportFragmentManager, "download-fragment")
//        fragment.job = job
    }
}

class WebDownloadSource constructor(private val documentURL: URL) : DownloadSource {
    /**
     * The open method needs to return an [InputStream] that will provide the complete document.
     */
    @Throws(IOException::class)
    override fun open(): InputStream {
        val connection = documentURL.openConnection() as HttpURLConnection
        connection.connect()
        return connection.inputStream
    }

    /**
     * If the length is available it can be returned here. This is optional, and can improve the reported download progress, since it will then contain
     * a percentage of download.
     */
    override fun getLength(): Long {
        var length = DownloadSource.UNKNOWN_DOWNLOAD_SIZE

        // We try to estimate the download size using the content length header.
        var urlConnection: URLConnection? = null
        try {
            urlConnection = documentURL.openConnection()
            val contentLength = urlConnection.contentLength
            if (contentLength != -1) {
                length = contentLength.toLong()
            }
        } catch (e: IOException) {
            Log.e(LOG_TAG, "Error while trying to parse the PDF Download URL.", e)
        } finally {
            (urlConnection as? HttpURLConnection)?.disconnect()
        }
        return length
    }

    override fun toString(): String {
        return "WebDownloadSource{documentURL=$documentURL}"
    }
}

private const val LOG_TAG = "DocumentDownloadExample"



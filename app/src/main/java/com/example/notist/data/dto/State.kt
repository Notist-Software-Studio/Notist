package com.example.notist.data.dto

import android.net.Uri
import com.pspdfkit.document.PdfDocument

data class State(
    val loading: Boolean = false,
    val documents: Map<Uri, PdfDocument> = emptyMap(),
    val selectedDocumentUri: Uri? = null
)
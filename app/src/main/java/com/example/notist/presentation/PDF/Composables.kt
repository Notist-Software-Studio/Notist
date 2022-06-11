package com.example.notist.presentation.PDF

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.notist.PDFMainViewModel
import com.example.notist.data.dto.State
import com.example.notist.navigation.NavRoutes
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.configuration.activity.UserInterfaceViewMode
import com.pspdfkit.document.PdfDocument
import com.pspdfkit.jetpack.compose.DocumentView
import com.pspdfkit.jetpack.compose.ExperimentalPSPDFKitApi
import com.pspdfkit.jetpack.compose.rememberDocumentState

private const val thumbnailPageIndex = 0

@Composable
@ExperimentalPSPDFKitApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
fun PdfList(
    state: State,
    viewModel: PDFMainViewModel,
    navController: NavController,
) {

    Scaffold(
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            AnimatedVisibility(
                state.loading,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                )  {

                    CircularProgressIndicator()
                }
            }

            AnimatedVisibility(
                visible = !state.loading && state.documents.isEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Button(onClick = { viewModel.loadPdfs() }) {
                        Text(
                            text = "load PDFs",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = !state.loading && state.documents.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {

                LazyVerticalGrid(
                    cells = GridCells.Adaptive(100.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items (state.documents.toList()) { (uri, document) ->
                        PdfThumbnail(
                            document = document,
                            onClick = {
                                viewModel
                                navController.navigate(NavRoutes.OpenPdf.route)
                            }
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = state.selectedDocumentUri != null,
                enter = slideInHorizontally(),
                exit = slideOutHorizontally(),
            ) {

                if (state.selectedDocumentUri == null) {
                    Box(Modifier.fillMaxSize())
                } else {
                    val context = LocalContext.current
                    val pdfActivityConfiguration = remember {
                        PdfActivityConfiguration
                            .Builder(context)
                            .setUserInterfaceViewMode(UserInterfaceViewMode.USER_INTERFACE_VIEW_MODE_HIDDEN)
                            .build()
                    }

                    val documentState = rememberDocumentState(
                        state.selectedDocumentUri,
                        pdfActivityConfiguration
                    )

                    DocumentView(
                        documentState = documentState,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PdfThumbnail(
    document: PdfDocument,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    // Since this can be a costly operation, we wanna memoize the
    // bitmap to prevent recalculating it every time we recompose.
    val thumbnailImage = remember(document) {
        val pageImageSize = document.getPageSize(thumbnailPageIndex).toRect()

        document.renderPageToBitmap(
            context,
            thumbnailPageIndex,
            pageImageSize.width().toInt(),
            pageImageSize.height().toInt()
        ).asImageBitmap()
    }

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp),
        onClick = onClick
    ) {
        Column {
            Image(
                bitmap = thumbnailImage,
                contentScale = ContentScale.Crop,
                contentDescription = "Preview for the document ${document.title}",
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = document.title ?: "Untitled Document",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(12.dp)
            )
        }

    }
}
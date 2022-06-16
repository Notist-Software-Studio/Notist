package com.example.notist.presentation.courses

import android.content.Intent
import com.pspdfkit.catalog.ui.model.State
import com.pspdfkit.catalog.ui.model.getPdfActivityConfigurationBuilder
import android.media.Image
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.notist.MainViewModel
import com.example.notist.PSPDFExample
import com.example.notist.R
import com.example.notist.data.dto.Course
import com.example.notist.data.dto.Pdf
import com.example.notist.presentation.PDF.DocumentDownloadExample
import com.example.notist.presentation.profile.ShareNote
import com.example.notist.presentation.profile.profileenter
import org.koin.core.KoinApplication.Companion.init


@Composable
fun UploadScreen(modifier: Modifier, incourseId: String,inclass_name: String,inmajor:String, navController: NavHostController,money : MutableState<Int>,viewModel: MainViewModel) {
    val pdfs by viewModel.downloadpdfs.observeAsState(initial = emptyList())
    val context = LocalContext.current
    var isShare = rememberSaveable { mutableStateOf(false)}
    Column(
        modifier
            .padding(vertical = 16.dp)
    ) {
        var pickedImageUri by remember { mutableStateOf<Uri?>(null) }
        viewModel.selectedCourseId = incourseId
        viewModel.fetchPdfs()
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val cursor = it.data?.data?.let { it1 ->
                    context.contentResolver.query(
                        it1,
                        null,
                        null,
                        null,
                        null
                    )
                }
                val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor?.getColumnIndex(OpenableColumns.SIZE)
                cursor?.moveToFirst()
                val fileName = nameIndex?.let { it1 -> cursor?.getString(it1) }
                val size = sizeIndex?.let { it1 -> cursor?.getLong(it1) }
                pickedImageUri = it.data?.data
//                Toast.makeText(context, pickedImageUri.toString(), Toast.LENGTH_LONG).show()
                val pdf =
                    fileName?.let { it1 ->
                        Pdf(
                            localUri = pickedImageUri.toString(),
                            filename = it1
                        )
                    }
                if (pdf != null) {
                    viewModel.pdfs.add(pdf)
                    if(viewModel.pdfs.isNotEmpty()){
                        viewModel.uploadPDF()
                        isShare.value = true
                    }
                }
            }
        ShareNote(isShare,money)
//        Button(
//            modifier = Modifier
//                .padding(30.dp)
//                .align(Alignment.BottomCenter),
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color(0xFF5C6BC0)
//            ),
//            onClick = {
//                val intent = Intent(
//                    Intent.ACTION_OPEN_DOCUMENT,
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                )
//                    .apply {
//                        addCategory(Intent.CATEGORY_OPENABLE)
//                    }
//                launcher.launch(intent)
//
//            }
//        ) {
//            Text("Select Document",color = Color.White)
//        }
//        AlignFileBar(modifier = Modifier, data = pdfs, navController)
        Box(modifier = Modifier.fillMaxSize()) {
            AlignFileBar(modifier = Modifier, data = pdfs, navController)
            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .height(50.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF5C6BC0)
                ),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_OPEN_DOCUMENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                        .apply {
                            addCategory(Intent.CATEGORY_OPENABLE)
                        }
                    launcher.launch(intent)

                }
            ) {
                Text("SELECT DOCUMENT", color = Color.White)
            }


        }
    }
}

@Composable
fun FileBar(
    localUri: String,
    remoteUri: String,
    id: String,
    filename: String,
    modifier: Modifier = Modifier, navController: NavHostController,
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState(State())
    Column(
        modifier = modifier
            .background(color = Color(0xFFC0C8D7), shape = RoundedCornerShape(10.dp))
            .width(370.dp)
            .height(60.dp)
            .clickable {
                viewModel.downloadPDF(context, remoteUri, filename)
            }
        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = filename,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = 16.dp
            )
        )
//        Text(
//            text = remoteUri,
//            color = Color.White,
//            style = MaterialTheme.typography.h6,
//            modifier = Modifier.padding(
//                start = 16.dp
//            )
//        )
//        Text(
//            text = id,
//            color = Color.White,
//            style = MaterialTheme.typography.h6,
//            modifier = Modifier.padding(
//                start = 16.dp
//            )
//        )
    }

}

@Composable
fun AlignFileBar(
    modifier: Modifier = Modifier,
    data: List<Pdf>,
    navController: NavHostController
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .paddingFromBaseline(bottom = 300.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data) { item ->
            FileBar(
                localUri = item.localUri,
                remoteUri = item.remoteUri,
                id = item.id,
                filename = item.filename,
                modifier = Modifier,
                navController,
                viewModel = MainViewModel()
            )
        }
    }
}






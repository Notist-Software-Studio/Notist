package com.example.notist.presentation.mylibrary


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notist.navigation.NavRoutes
import com.pspdfkit.jetpack.compose.ExperimentalPSPDFKitApi


@OptIn(ExperimentalPSPDFKitApi::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable

fun MyLibrary(navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {
        Button(onClick = { navController.navigate(NavRoutes.PdfList.route) }) {
            Text(
                text = "course name",
                textAlign = TextAlign.Center
            )
        }
    }
}




// fun MyLibrary(navController: NavHostController) {
//     Box(modifier = Modifier.fillMaxSize()) {
//         ScaffoldWithTopBarMyLibrary(navController)
//     }
// }


// @Composable
// fun ScaffoldWithTopBarMyLibrary(navController: NavHostController) {
//     Scaffold(
//         topBar = {

//         }, content = {
//             Column(
//                 modifier = Modifier.fillMaxSize(),
//                 verticalArrangement = Arrangement.Top,
//                 horizontalAlignment = Alignment.CenterHorizontally
//             ) {

//                 Spacer(modifier = Modifier.height(30.dp))
//                 Box(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)) {

//                     Button(
//                         onClick = { },
//                         shape = RoundedCornerShape(10.dp),
//                         modifier = Modifier
//                             .fillMaxWidth()
//                             .height(50.dp),
//                         colors = ButtonDefaults.buttonColors(
//                             backgroundColor = (Color.White),
//                             contentColor = Color.Gray
//                         )

//                         //.padding(vertical = 12.dp, horizontal = 20.dp)
//                     ) {
//                         Text(text = "Search for notes",textAlign = TextAlign.Left, modifier = Modifier.size(250.dp,20.dp),
//                             color = Color(0xB8B7B3B9)
//                         )
//                         //Spacer(modifier = Modifier.height(20.dp))

//                     }
//                     Icon(
//                         painter = painterResource(id = R.drawable.ic_action_search),
//                         contentDescription = "Localized description",
//                         modifier = Modifier.padding(vertical = 13.dp, horizontal = 20.dp) ,
//                         tint = Color(0xB8B7B3B9)
//                     )
//                 }
//                 Spacer(modifier = Modifier.height(20.dp))
//                 Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                     Button(
//                        onClick = { },
//                         shape = RoundedCornerShape(10.dp),
//                         modifier = Modifier
//                             .fillMaxWidth()
//                             .height(30.dp)
//                             .padding(vertical = 0.dp, horizontal = 60.dp),
//                         colors = ButtonDefaults.buttonColors(
//                             backgroundColor = (Color(0xE0DCDCDC)),
//                             contentColor = Color.White

//                         )
//                         //CS  EECS  EE
//                     ) {

//                         Text(text = "All",fontSize = 12.sp,textAlign = TextAlign.Left)
//                         Spacer(modifier = Modifier.width(20.dp))
//                         Text(text = "CS",fontSize = 12.sp,textAlign = TextAlign.Left)
//                         Spacer(modifier = Modifier.width(20.dp))
//                         Text(text = "EECS",fontSize = 12.sp,textAlign = TextAlign.Left)
//                         Spacer(modifier = Modifier.width(20.dp))
//                         Text(text = "EE",fontSize = 12.sp,textAlign = TextAlign.Left)
//                     }
//                 }
//                Spacer(modifier = Modifier.height(20.dp))
//                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                    Button(
//                        onClick = { navController.navigate(Routes.LinearAlgebra.route) },
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(55.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = (Color(0x74828B9B))
//                        )
//                    ) {
//                        Column(horizontalAlignment = Alignment.Start,modifier = Modifier.size(300.dp,50.dp)){
//                            Text(text = "Linear Algebra",fontSize = 16.sp,textAlign = TextAlign.Left,fontWeight = FontWeight.Bold)
//                            Text(text = "EECS",fontSize = 12.sp,textAlign = TextAlign.Left,color = Color.White)
//                        }
//                    }
//                }
//                Spacer(modifier = Modifier.height(20.dp))
//                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                    Button(
//                        onClick = { navController.navigate(Routes.LogicDesign.route) },
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(55.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = (Color(0x74828B9B))
//                        )
//                    ) {
//                        Column(horizontalAlignment = Alignment.Start,modifier = Modifier.size(300.dp,50.dp)){
//                            Text(text = "Logic Design",fontSize = 16.sp,textAlign = TextAlign.Left,fontWeight = FontWeight.Bold)
//                            Text(text = "EECS",fontSize = 12.sp,textAlign = TextAlign.Left,color = Color.White)
//                        }
//                    }
//                }
//                Spacer(modifier = Modifier.height(20.dp))
//                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                    Button(
//                        onClick = { navController.navigate(Routes.DataStructures.route) },
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(55.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = (Color(0x74828B9B))
//                        )
//                    ) {
//                        Column(horizontalAlignment = Alignment.Start,modifier = Modifier.size(300.dp,50.dp)){
//                            Text(text = "Data Structures",fontSize = 16.sp,textAlign = TextAlign.Left,fontWeight = FontWeight.Bold)
//                            Text(text = "EECS",fontSize = 12.sp,textAlign = TextAlign.Left,color = Color.White)
//                        }
//                    }
//                }

                /*Spacer(modifier = Modifier.height(20.dp))
                ClickableText(
                    text = AnnotatedString("Forgot password?"),
                    onClick = { navController.navigate(Routes.ForgotPassword.route) },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default
                    )
                )*/
//             }

//         })
// }
// @Preview(showBackground = true)
// @Composable
// fun DefaultPreview1() {
//     val navController = rememberNavController()
//     MyLibrary(navController = navController)

// }
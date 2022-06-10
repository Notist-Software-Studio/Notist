package com.example.notist.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.rememberNavController
import com.example.notist.R
import com.example.notist.ui.theme.Purple500

@Composable
fun profilesettings(isDialogOpen: MutableState<Boolean>) {
    val usernameVal = remember { mutableStateOf("") }
    val emailVal = remember { mutableStateOf("") }
    val passwordVal = remember { mutableStateOf("") }
    var thumbIconLiked = remember { mutableStateOf("") }
    val passwordVisibility = remember {
        mutableStateOf(false)
    }
    val focusRequester = remember {
        FocusRequester
    }

    if (isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Surface(
                modifier = Modifier
                    .width(450.dp)
                    .height(600.dp)
                    .padding(0.dp),
                shape = RoundedCornerShape(30.dp),
                color = Color.White
            ) {

                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier.height(60.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Top
                    ) {
                        Spacer(modifier = Modifier.width(270.dp))
                        IconButton(
                            onClick = { isDialogOpen.value = false },
                            modifier = Modifier.size(30.dp, 50.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.close),
                                contentDescription = "Localized description",
                                modifier = Modifier
                                    .size(60.dp, 30.dp),
                                tint = Color(0xB8000000)
                            )

                        }
                    }
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.padding(80.dp,0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(150.dp, 130.dp)
                        )

                        FloatingActionButton(
                            modifier = Modifier.padding(90.dp,0.dp,0.dp,0.dp),
                            backgroundColor = Color(0xB85C6BC0),
                            onClick = {

                            }) {
                            Icon(painter = painterResource(id = R.drawable.ic_action_pencil),
                                contentDescription = "Localized description",
                                modifier = Modifier
                                    .size(30.dp, 30.dp),
                                tint = Color(0xB8FFFFFF)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {


//
                    Spacer(modifier = Modifier.padding(100.dp))

                    TextField(
                        shape = RoundedCornerShape(20.dp),
                        value = usernameVal.value,
                        onValueChange = { usernameVal.value = it },
                        label = { Text(text = "Username:") },
                        placeholder = { Text(text = "Username") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )


                    Spacer(modifier = Modifier.padding(10.dp))

                    TextField(
                        shape = RoundedCornerShape(20.dp),
                        value = emailVal.value,
                        onValueChange = { emailVal.value = it },
                        label = { Text(text = "Email Address:") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    TextField(
                        shape = RoundedCornerShape(20.dp),
                        value = passwordVal.value,
                        onValueChange = { passwordVal.value = it },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisibility.value = !passwordVisibility.value
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                                    contentDescription = "",
                                    tint = if (passwordVisibility.value) Purple500 else Color.Gray
                                )
                            }
                        },
                        label = { Text(text = "Password:") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
                            PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = {
                            isDialogOpen.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(60.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xB85C6BC0)
                        )
                    ) {
                        Text(
                            text = "SAVE",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview10() {
    val navController = rememberNavController()
    val isDialogOpen = remember { mutableStateOf(true)}
    profilesettings(isDialogOpen)
}

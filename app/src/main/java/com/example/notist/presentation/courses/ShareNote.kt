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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
fun ShareNote(isShare: MutableState<Boolean>,money : MutableState<Int>) {

    if (isShare.value) {
        Dialog(onDismissRequest = {  }) {
            Surface(
                modifier = Modifier
                    .width(400.dp)
                    .height(550.dp)
                    .padding(0.dp),
                shape = RoundedCornerShape(30.dp),
                color = Color.White
            ) {

                Column(
                    modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.congratulation),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(200.dp, 180.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "Congratulations!",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF000000)
                        )
                    )


                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "You have just earned 20 coins by",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF000000)
                        )
                    )
                    Text(
                        text = "sharing your notes to others",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF000000)
                        )
                    )


                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = {
                            isShare.value = false
                            money.value += 20
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(70.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xB85C6BC0)
                        )
                    ) {
                        Text(
                            text = "RETURN",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview30() {
    val navController = rememberNavController()
    val isShare = remember { mutableStateOf(true)}
    var money = remember { mutableStateOf(20) }
    ShareNote(isShare,money)
}


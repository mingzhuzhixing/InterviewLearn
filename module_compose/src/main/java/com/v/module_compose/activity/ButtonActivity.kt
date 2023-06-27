package com.v.module_compose.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v.module_compose.R
import com.v.module_compose.ui.theme.InterviewLearnTheme
import com.v.module_compose.widget.appBar

class ButtonActivity : ComponentActivity() {
    lateinit var mActivity: Activity;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        setContent {
            InterviewLearnTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xfff1f1f1)
                ) {
                    MainContent()
                }
            }
        }
    }

    @Composable
    fun MainContent() {
        Column() {
            appBar("Button库", "") {
                mActivity.onBackPressed()
            }
            Button(onClick = {
                Toast.makeText(mActivity, "我被点击了", Toast.LENGTH_LONG).show()
            }) {
                Text(text = "Button按钮")
            }
            Button(
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(1.dp, color = Color.Red),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Yellow
                ),
                onClick = {
                    Toast.makeText(mActivity, "我被点击了", Toast.LENGTH_LONG).show()
                },
            ) {
                Text(text = "Button按钮")
            }

            TextButton(onClick = {}) {
                Text(text = "TextButton按钮")
            }

            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.card_play_video),
                    contentDescription = null
                )
            }

            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "OutlinedButton按钮")
            }

            RadioButton(selected = true, onClick = { /*TODO*/ })
            RadioButton(selected = false, onClick = { /*TODO*/ })

            FloatingActionButton(onClick = { /*TODO*/ }) {
                Text(text = "F钮")
            }

            ExtendedFloatingActionButton(
                text = { Text(text = "ExtendedFloatingActionButton按钮") },
                onClick = { /*TODO*/ })

            val checkedState = remember { mutableStateOf(true) }
            IconToggleButton(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = if (checkedState.value) {
                        Color.Red
                    } else {
                        Color.Gray
                    }
                )
            }
            
            ElevatedButton(onClick = { /*TODO*/ }) {
                Text(text = "ElevatedButton按钮")
            }

            FilledTonalButton(onClick = { /*TODO*/ }) {
                Text(text = "FilledTonalButton按钮")
            }

            SmallFloatingActionButton(onClick = { /*TODO*/ }) {
                Text(text = "SmallFloatingActionButton按钮")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainContent()
    }
}
package com.v.module_compose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.v.module_compose.MainComposeActivity.Companion.mActivity
import com.v.module_compose.activity.ButtonActivity
import com.v.module_compose.activity.TextFieldActivity
import com.v.module_compose.activity.ImageActivity
import com.v.module_compose.activity.column.LazyColumnActivity
import com.v.module_compose.ui.theme.InterviewLearnTheme
import com.v.module_compose.widget.appBar

class MainComposeActivity : ComponentActivity() {
    companion object {
        lateinit var mActivity: Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        setContent {
            InterviewLearnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    Column(
            modifier = Modifier.background(color = Color(0xfff1f1f1)),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        appBar("Compose组件", "") {
            mActivity.onBackPressed()
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "欢迎来到compose组件学习，祝你学习愉快!!")
        Spacer(modifier = Modifier.height(10.dp))
        Button(
                onClick = {
                    mActivity.startActivity(Intent(mActivity, ButtonActivity::class.java))
                }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(text = "Button库")
        }

        Button(
                onClick = {
                    mActivity.startActivity(Intent(mActivity, TextFieldActivity::class.java))
                }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(text = "TextField库")
        }

        Button(
                onClick = {
                    mActivity.startActivity(Intent(mActivity, LazyColumnActivity::class.java))
                }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(text = "LazyColumn")
        }

        Button(
                onClick = {
                    mActivity.startActivity(Intent(mActivity, ImageActivity::class.java))
                }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Text(text = "Image")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InterviewLearnTheme {
        MainContent()
    }
}
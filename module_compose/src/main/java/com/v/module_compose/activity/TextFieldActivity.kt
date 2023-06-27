package com.v.module_compose.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.v.module_compose.ui.theme.InterviewLearnTheme
import com.v.module_compose.widget.appBar

/**
 * ClassName: ComposeTextFieldActivity
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_compose.activity
 * @date 2023/6/18 17:42
 */
class TextFieldActivity : ComponentActivity() {
    lateinit var mActivity: Activity

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
            appBar("TextField库", "") {
                mActivity.onBackPressed()
            }
            Spacer(modifier = Modifier.height(10.dp))
            var inputText by remember { mutableStateOf("") }
            TextField(
                value = inputText,
                onValueChange = {
                    inputText = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Green,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                placeholder = {
                    Text(text = "请输入内容")
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            var outlinedInputText by remember { mutableStateOf("") }
            OutlinedTextField(
                value = outlinedInputText,
                onValueChange = {
                    outlinedInputText = it
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.Gray,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            Toast.makeText(mActivity, "点击搜索", Toast.LENGTH_LONG).show()
                        })
                },
                trailingIcon = {
                    if (outlinedInputText.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                outlinedInputText = ""
                            })
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Mic,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                Toast.makeText(mActivity, "我是被点击了", Toast.LENGTH_LONG).show()
                            })
                    }
                },
                placeholder = {
                    Text(text = "搜索")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
            inputNumber()
        }
    }

    @Composable
    fun inputNumber() {
        ConstraintLayout(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
            val (text_input, text) = createRefs()
            var inputNumber by remember { mutableStateOf(0); }
            var inputValue by remember { mutableStateOf(TextFieldValue()) }
            TextField(
                value = inputValue,
                onValueChange = {
                    if (it.text.length <= 50) {
                        inputValue = it
                        inputNumber = it.text.length
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .constrainAs(text_input) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .height(150.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(text = "请输入内容")
                }
            )

            Text("$inputNumber/50", Modifier.constrainAs(text) {
                bottom.linkTo(parent.bottom, margin = 10.dp)
                end.linkTo(parent.end, margin = 20.dp)
            })
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainContent()
    }
}
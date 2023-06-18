package com.v.module_compose.activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
class ComposeTextFieldActivity  : ComponentActivity() {
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
            appBar("TextField库", "") {
                mActivity.onBackPressed()
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainContent()
    }
}
package com.v.module_compose.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.v.module_compose.R
import com.v.module_compose.activity.ImageActivity.Companion.mActivity
import com.v.module_compose.ui.theme.InterviewLearnTheme
import com.v.module_compose.widget.appBar

/**
 */
class ImageActivity : ComponentActivity() {
    companion object {
        lateinit var mActivity: Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        setContent {
            MainContent()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent() {
    Scaffold(
            topBar = {
                appBar("Image库", "") {
                    mActivity.onBackPressed()
                }
            }
    ) {
        Column(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .verticalScroll(state = rememberScrollState())
        ) {
            // 从磁盘加载图片
            Image(
                    painter = painterResource(id = R.drawable.icon_car),
                    contentDescription = stringResource(id = R.string.string_car),
                    modifier = Modifier.size(width = 150.dp, height = 150.dp),
            )

            //圆图片
            Image(
                    painter = painterResource(id = R.drawable.dog),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
            )

            //圆角图片
            Image(
                    painter = painterResource(id = R.drawable.dog),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(16.dp))
            )

            //图片添加边框
            val borderWidth = 4.dp
            Image(
                    painter = painterResource(id = R.drawable.dog),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .size(150.dp)
                            .border(
                                    BorderStroke(width = borderWidth, color = Color.Yellow),
                                    CircleShape
                            )
                            .padding(borderWidth)
                            .clip(CircleShape)
            )

            //自定义图片宽高比
            Image(
                    painter = painterResource(id = R.drawable.dog),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.aspectRatio(16f / 9f)
            )

            // 转换图片的像素颜色
            Image(
                    painter = painterResource(id = R.drawable.dog),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Green, blendMode = BlendMode.Darken)
            )

            //图片模糊处理
            Image(
                    painter = painterResource(id = R.drawable.dog),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                            .size(150.dp)
                            .blur(
//                                    radiusX = 10.dp,
//                                    radiusY = 10.dp,
                                    radius = 10.dp,
                                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                            )
            )

            //网络加载图片
            AsyncImage(
                    model = "https://t7.baidu.com/it/u=1883040060,1157514614&fm=193&f=GIF",
                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
            )
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
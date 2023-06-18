package com.v.module_compose.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.v.module_compose.R

/**
 * ClassName: TitleBar
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_compose.widget
 * @date 2023/6/18 16:24
 */
@Composable
fun appBar(title: String, sub_title: String, click: () -> Unit) {
    Row(
        modifier = Modifier
            .background(color = Color.White)
            .padding(start = 10.dp, end = 10.dp)
            .height(44.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "返回按鈕",
            modifier = Modifier
                .width(60.dp)
                .height(20.dp)
                .clickable(
                    onClick = click,
                    // 去除点击效果
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                ),
            alignment = Alignment.CenterStart
        )
        Text(
            text = title,
            modifier = Modifier.weight(1.0f),
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Text(
            text = sub_title, modifier = Modifier.width(60.dp),
            textAlign = TextAlign.Right
        )
    }
}
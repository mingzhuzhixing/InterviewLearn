package com.v.module_compose.activity.column

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.v.module_compose.ui.theme.InterviewLearnTheme

/**
 * Compose 提供了一组组件，这些组件只会对在组件视口中可见的列表项进行组合和布局。这些组件包括 LazyColumn 和 LazyRow。
 */
class LazyColumnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}

@Composable
fun MainContent() {
    LazyColumn {
        item{
            Text(text = "First item")
        }
        // Add 5 items
        items(5) { index ->
            Text(text = "Item: $index")
        }

        // Add another single item
        item {
            Text(text = "Last item")
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
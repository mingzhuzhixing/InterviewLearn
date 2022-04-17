package com.v.kotlin.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() {
    println("1234")

    runBlocking {
        launch {
            println("hello world")
        }
    }

    GlobalScope.launch (Dispatchers.IO){
        println("hello world 2222")
    }
}
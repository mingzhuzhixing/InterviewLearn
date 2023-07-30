package com.v.kotlin_app.simple

/**
 * 单利模式
 */
class NetManager2 {

    companion object {
        private var instance: NetManager2? = null
        fun getInstance(): NetManager2 {
            if (instance == null) {
                instance = NetManager2();
            }
            return instance!!
        }
    }

    fun show(name: String) {

    }

}

fun main() {
    val instance = NetManager2.getInstance()
    instance.show("aaa")
}
package com.v.kotlin_app.simple

/**
 * 单利模式
 */
class NetManager {
    //只有一个实例
    object Holder {
        val instance = NetManager()
    }

    //看不到static 可以使用派生类
    companion object {
        //全部都是static  相当于java static
        fun getInstance(): NetManager = Holder.instance
    }

    fun show(name: String) {
        print("show:$name")
    }
}

fun main() {
    val net = NetManager.getInstance()
    net.show("kt ha")
}
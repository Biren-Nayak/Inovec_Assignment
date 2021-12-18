package com.example.inovecassignment.api.key

object Keys {

    init {
        System.loadLibrary("native-lib")
    }
    val apikey = apiKey()
    private external fun apiKey(): String
}
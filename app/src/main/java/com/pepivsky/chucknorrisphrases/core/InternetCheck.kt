package com.pepivsky.chucknorrisphrases.core

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket

object InternetCheck {

    suspend fun isNetworkAvailable() = withContext(Dispatchers.IO) {
            Log.d("InternetCheck", "isNetworkAvailable: ")
            return@withContext try {
                val sock = Socket()
                val socketAddress = InetSocketAddress("8.8.8.8", 53)
                sock.connect(socketAddress, 1000)
                sock.close()
                Log.d("InternetCheck", "cerrando socket: ")
                true
            } catch (e: Exception) {
                false
            }
    }
}
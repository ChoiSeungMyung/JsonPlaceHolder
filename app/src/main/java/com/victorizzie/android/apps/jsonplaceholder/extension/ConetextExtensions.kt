package com.victorizzie.android.apps.jsonplaceholder.extension

import android.content.Context
import android.net.ConnectivityManager

/**
 * 네트워크 상태 체크를 위한 확장함수
 */
fun Context.isDeviceOnline(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
}
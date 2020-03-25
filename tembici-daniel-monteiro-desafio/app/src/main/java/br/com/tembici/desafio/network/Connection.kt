package br.com.tembici.desafio.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * @author Daniel Monteiro
 *
 * @since on 25/03/2020
 *
 */

class Connection {
    companion object {

        @SuppressLint("MissingPermission")
        fun isConexaoInternet(context: Context): Boolean {

            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                val network = connectivityManager.activeNetwork
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))

            } else {
                connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI ||
                        connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
            }

        }

    }
}
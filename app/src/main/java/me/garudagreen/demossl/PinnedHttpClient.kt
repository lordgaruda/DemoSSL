package me.garudagreen.demossl

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import android.util.Log

object PinnedHttpClient {
    fun makeRequest(): Boolean {
        return try {
            val hostname = "api.garudaext.tech"

            val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/AzkiMBHRPRdKIdXZET9giP3SCFo2/BZPT+9xObAOzek=") // Replace this
                .build()

            val client = OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .build()

            val request = Request.Builder()
                .url("https://$hostname/")
                .build()

            val response = client.newCall(request).execute()
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("SSL_PINNING", "Error: ${e.message}")
            false
        }
    }
}

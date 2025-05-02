package me.garudagreen.demossl

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import java.security.cert.Certificate
import android.util.Log

object PinnedHttpClient {
    fun makeRequest(): Boolean {
        return try {
            val hostname = "api.yourdomain.com"

            val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/<yourpublickey>") // Replace this
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

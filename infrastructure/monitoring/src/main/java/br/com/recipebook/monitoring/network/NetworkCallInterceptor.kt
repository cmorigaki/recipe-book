package br.com.recipebook.monitoring.network

import android.os.SystemClock
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.monitoring.network.analytics.NetworkCall
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

internal class NetworkCallInterceptor(
    private val analytics: Analytics,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = SystemClock.elapsedRealtime()
        try {
            return chain.proceed(request).also {
                sendNetworkEvent(startTime = startTime, request = request, response = it)
            }
        } catch (exception: IOException) {
            sendNetworkEvent(startTime = startTime, request = request, exception = exception)
            throw exception
        }
    }

    private fun sendNetworkEvent(
        startTime: Long,
        request: Request,
        response: Response? = null,
        exception: Exception? = null
    ) {
        val duration = SystemClock.elapsedRealtime() - startTime
        analytics.sendEvent(
            NetworkCall(
                time = duration,
                method = request.method,
                host = request.url.host,
                path = request.url.encodedPath,
                query = request.url.query,
                status = response?.code,
                exception = exception?.message,
            )
        )
    }
}

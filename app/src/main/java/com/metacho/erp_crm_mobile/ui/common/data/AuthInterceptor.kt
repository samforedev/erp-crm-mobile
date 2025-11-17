package com.metacho.erp_crm_mobile.ui.common.data

import com.metacho.erp_crm_mobile.ui.data.UserPreferences
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val userPrefs: UserPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = runBlocking { userPrefs.token.firstOrNull() }

        val newRequest = if (!token.isNullOrEmpty()) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(newRequest)
    }
}



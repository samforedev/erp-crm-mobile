package com.metacho.erp_crm_mobile

import com.metacho.erp_crm_mobile.ui.common.data.AuthInterceptor
import com.metacho.erp_crm_mobile.ui.customer.data.ICustomerApiService
import com.metacho.erp_crm_mobile.ui.customer.domain.CustomerRepository
import com.metacho.erp_crm_mobile.ui.data.UserPreferences
import com.metacho.erp_crm_mobile.ui.login.data.ILoginApiService
import com.metacho.erp_crm_mobile.ui.login.domain.LoginRepository
import com.metacho.erp_crm_mobile.ui.user.data.IUserApiService
import com.metacho.erp_crm_mobile.ui.user.domain.UserRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    fun provideOkHttp(userPrefs: UserPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(userPrefs))
            .build()
    }

    fun provideProtectedRetrofit(userPrefs: UserPreferences): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.metacho.com/api/v1/")
            .client(provideOkHttp(userPrefs))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideLoginRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://metacho.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideLoginApi(retrofit: Retrofit): ILoginApiService =
        retrofit.create(ILoginApiService::class.java)

    fun provideUserApi(retrofit: Retrofit): IUserApiService =
        retrofit.create(IUserApiService::class.java)

    fun provideCustomerApi(retrofit: Retrofit): ICustomerApiService =
        retrofit.create(ICustomerApiService::class.java)

    fun provideLoginRepository(api: ILoginApiService) =
        LoginRepository(api)

    fun provideUserRepository(api: IUserApiService) =
        UserRepository(api)

    fun provideCustomerRepository(api: ICustomerApiService) =
        CustomerRepository(api)
}
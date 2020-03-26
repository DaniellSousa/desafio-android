package br.com.tembici.desafio.network

import br.com.tembici.desafio.model.PullRequest
import br.com.tembici.desafio.model.RepositoriesReturn
import br.com.tembici.desafio.services.Services
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Daniel Monteiro
 *
 * @since on 25/03/2020
 *
 */

class RetrofitInitApi {
    private val services: Services

    private fun getRequestHEaderOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1800, TimeUnit.SECONDS)
            .connectTimeout(1800, TimeUnit.SECONDS)
            .writeTimeout(900, TimeUnit.SECONDS)
            .build()
    }

    companion object {
        public val BASE_URL = "https://api.github.com/"
        private var apiInit: RetrofitInitApi? = null

        val instance: RetrofitInitApi get() {
            if (apiInit == null) {
                apiInit = RetrofitInitApi()
            }

            return apiInit as RetrofitInitApi
        }
    }

    init {
        val gson = GsonBuilder().setLenient().create()

        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getRequestHEaderOkHttp())
            .build()

        services = retrofit.create(Services::class.java)
    }

    fun getRepositories(page: Int): Observable<Response<RepositoriesReturn>> {
        return services.getRepositories(page)
    }

    fun getPullRequests(loginUser: String, idRepository: String): Observable<Response<ArrayList<PullRequest>>> {
        return services.getPullRequests(loginUser, idRepository)
    }

}
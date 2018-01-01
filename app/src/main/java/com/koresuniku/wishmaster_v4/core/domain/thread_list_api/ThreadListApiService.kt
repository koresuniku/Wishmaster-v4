package com.koresuniku.wishmaster_v4.core.domain.thread_list_api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by koresuniku on 01.01.18.
 */

interface ThreadListApiService {

    @GET("/{id}/catalog.json")
    fun getThreadsObservable(@Path("id") boardId: String): Observable<ThreadListJsonSchemaResponse>

    @GET("/{id}/{page}.json")
    fun getThreadsByPageObservable(@Path("id") boardId: String, @Path("page") page: String): Observable<ThreadListJsonSchemaResponse>
}
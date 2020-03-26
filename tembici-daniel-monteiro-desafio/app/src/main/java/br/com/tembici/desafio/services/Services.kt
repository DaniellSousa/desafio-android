package br.com.tembici.desafio.services

import br.com.tembici.desafio.model.PullRequest
import br.com.tembici.desafio.model.RepositoriesReturn
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Daniel Monteiro
 *
 * @since on 22/072018
 *
 * */

interface Services {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepositories(@Query("page") page: Int?): Observable<Response<RepositoriesReturn>>

    @GET("repos/{login_owner}/{id_repository}/pulls")
    fun getPullRequests(@Query("login_owner") loginOwner: String?,
                        @Query("id_repository") idRepository: String?): Observable<Response<ArrayList<PullRequest>>>

}
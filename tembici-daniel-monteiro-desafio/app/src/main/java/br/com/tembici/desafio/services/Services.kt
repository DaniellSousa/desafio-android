package br.com.tembici.desafio.services

import br.com.tembici.desafio.model.PullRequest
import br.com.tembici.desafio.model.RepositoriesReturn
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("repos/{login_owner}/{name_repository}/pulls")
    fun getPullRequests(@Path("login_owner") loginOwner: String?,
                        @Path("name_repository") nameRepository: String?): Observable<Response<ArrayList<PullRequest>>>

}
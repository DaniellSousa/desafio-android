package br.com.tembici.desafio.presenter.view

import br.com.tembici.desafio.model.PullRequest
import br.com.tembici.desafio.model.RepositoriesReturn
import retrofit2.Response

/**
 * @author Daniel Monteiro
 *
 * @since 26/03/2020
 *
 */


interface PullRequestsPresenterView {

    interface ViewReturnPullRequests {
        fun showProgressLoading()
        fun hideProgressLoading()
        fun onSuccess(responseList: Response<ArrayList<PullRequest>>)
        fun onError()
        fun intetnetError()
        fun checkInternet(): Boolean
    }

    interface MainDataPresenterView {
        fun getPullRequests(loginOwner: String, nameRepository: String)
    }
}
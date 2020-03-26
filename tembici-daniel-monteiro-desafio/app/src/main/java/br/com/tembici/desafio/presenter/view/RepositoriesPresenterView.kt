package br.com.tembici.desafio.presenter.view

import br.com.tembici.desafio.model.RepositoriesReturn
import retrofit2.Response

/**
 * @author Daniel Monteiro
 *
 * @since on 25/03/2020
 *
 * */

interface RepositoriesPresenterView {

    interface ViewReturnRepositories {
        fun showProgressLoading()
        fun hideProgressLoading()
        fun onSuccess(responseList: Response<RepositoriesReturn>)
        fun onError()
        fun intetnetError()
        fun checkInternet(): Boolean
    }

    interface MainDataPresenterView {
        fun getRepositories(page: Int)
    }
}
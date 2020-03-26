package br.com.tembici.desafio.presenter.data

import br.com.tembici.desafio.network.RetrofitInitApi
import br.com.tembici.desafio.presenter.view.PullRequestsPresenterView
import br.com.tembici.desafio.presenter.view.RepositoriesPresenterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author Daniel Monteiro
 *
 * @since 25/03/2020
 *
 */


class PullRequestsPresenterData: PullRequestsPresenterView.MainDataPresenterView {

    var viewReturnPullRequests: PullRequestsPresenterView.ViewReturnPullRequests? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(view: PullRequestsPresenterView.ViewReturnPullRequests) {
        this.viewReturnPullRequests = view
    }

    override fun getPullRequests(loginOwner: String, nameRepository: String) {
        this.viewReturnPullRequests!!.showProgressLoading()

        if (this.viewReturnPullRequests!!.checkInternet()) {
            this.disposable = RetrofitInitApi.instance.getPullRequests(loginOwner,
                nameRepository)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    returnPullRequest ->

                    this.viewReturnPullRequests!!.hideProgressLoading()

                    when(returnPullRequest.code()) {
                        200, 201, 202 -> { this.viewReturnPullRequests!!.onSuccess(returnPullRequest) }
                    }
                }, { error ->
                    this.viewReturnPullRequests!!.hideProgressLoading()
                    this.viewReturnPullRequests!!.onError()
                })
        }else {
            this.viewReturnPullRequests!!.hideProgressLoading()
            this.viewReturnPullRequests!!.intetnetError()
        }
    }

}
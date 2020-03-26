package br.com.tembici.desafio.presenter.data

import br.com.tembici.desafio.network.RetrofitInitApi
import br.com.tembici.desafio.presenter.view.RepositoriesPresenterView
import io.reactivex.Scheduler
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

class RepositoriesPresenterData: RepositoriesPresenterView.MainDataPresenterView {

    var viewReturnRepositories: RepositoriesPresenterView.ViewReturnRepositories? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(view: RepositoriesPresenterView.ViewReturnRepositories) {
        this.viewReturnRepositories = view
    }

    override fun getRepositories(page: Int) {
        this.viewReturnRepositories!!.showProgressLoading()

        if (this.viewReturnRepositories!!.checkInternet()) {
            this.disposable = RetrofitInitApi.instance.getRepositories(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    returnRepositories ->
                    this.viewReturnRepositories!!.hideProgressLoading()

                    when(returnRepositories.code()) {
                        200, 201, 202 -> { this.viewReturnRepositories!!.onSuccess(returnRepositories) }
                    }

                }, { error ->
                    this.viewReturnRepositories!!.hideProgressLoading()
                    this.viewReturnRepositories!!.onError()
                })
        }else {
            this.viewReturnRepositories!!.hideProgressLoading()
            this.viewReturnRepositories!!.intetnetError()
        }
    }

}
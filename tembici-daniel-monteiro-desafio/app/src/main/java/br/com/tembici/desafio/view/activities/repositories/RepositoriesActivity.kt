package br.com.tembici.desafio.view.activities.repositories

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import br.com.tembici.desafio.R
import br.com.tembici.desafio.model.ItemRepository
import br.com.tembici.desafio.model.RepositoriesReturn
import br.com.tembici.desafio.network.Connection
import br.com.tembici.desafio.presenter.data.RepositoriesPresenterData
import br.com.tembici.desafio.presenter.view.RepositoriesPresenterView
import br.com.tembici.desafio.view.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_repositories.*
import retrofit2.Response

/**
 * @author Daniel Monteiro
 *
 * @since 25/03/2020
 *
 */

class RepositoriesActivity : BaseActivity(), AbsListView.OnScrollListener,
    RepositoriesPresenterView.ViewReturnRepositories {

    var listAllRepositories: ArrayList<ItemRepository>? = ArrayList<ItemRepository>()
    var qtdListaCurrentePage: Int = 0

    private var visibleThreshold = 5
    private var currentPage = 0
    private var previousTotal = 0
    private var loading = false
    var isReloadPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        setUpInicialConfigActivity()

        lvRepositories.setOnScrollListener(this)
    }

    private fun setAdapterList(listItems: ArrayList<ItemRepository>) {
        lvRepositories.visibility = View.VISIBLE
        val adapter = RepositoriesAdapter(this, listItems)
        lvRepositories.adapter = adapter

        if (isReloadPage && currentPage > 1) {
            lvRepositories.setSelection((listAllRepositories!!.size - qtdListaCurrentePage - 5))
        }
    }

    private fun callRepositoriesRequest() {
        val repositoryPresenterData = RepositoriesPresenterData(this)
        repositoryPresenterData.getRepositories(this.currentPage)
    }

    override fun onScroll(p0: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                isReloadPage = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            currentPage += 1
            callRepositoriesRequest()
            loading = true
            isReloadPage = true
        }

    }

    override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
//        showMessageToast(this, R.string.msg_click, true)
    }

    override fun showProgressLoading() {
        pbLoading.visibility = View.VISIBLE
    }

    override fun hideProgressLoading() {
        pbLoading.visibility = View.GONE
    }

    override fun onSuccess(responseList: Response<RepositoriesReturn>) {
        val returnListRepositories = responseList.body()!!
        qtdListaCurrentePage = responseList.body()!!.items.size
        this.listAllRepositories!!.addAll(returnListRepositories.items)
        setAdapterList(this.listAllRepositories!!)
    }

    override fun onError() {
        showMessage(this, R.string.title_error, R.string.msg_error, 0, false)
    }

    override fun intetnetError() {
        showMessage(this, R.string.title_error, R.string.msg_error, 0, false)
    }

    override fun checkInternet(): Boolean {
        return Connection.isConexaoInternet(this)
    }



}
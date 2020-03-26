package br.com.tembici.desafio.view.activities.repositories

import android.os.Bundle
import android.util.Log
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

    override fun showProgressLoading() {
        showProgressDialog()
    }

    override fun hideProgressLoading() {
        dismissProgressDailog()
    }

    override fun onSuccess(responseList: Response<RepositoriesReturn>) {
        val returnListRepositories = responseList.body()!!
        this.listAllRepositories!!.addAll(returnListRepositories.items)
        setAdapterList(this.listAllRepositories!!)
    }

    override fun onError() {

    }

    override fun intetnetError() {

    }

    override fun checkInternet(): Boolean {
        return Connection.isConexaoInternet(this)
    }

    private var visibleThreshold = 5
    private var currentPage = 1
    private var previousTotal = 0
    private var loading = false
    var isReloadPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        setUpInicialConfigActivity()

        lvRepositories.setOnScrollListener(this)

        callRepositoriesRequest()
    }

    private fun setAdapterList(listItems: ArrayList<ItemRepository>) {
        val adapter = RepositoriesAdapter(this, listItems)
        lvRepositories.adapter = adapter

        // Revisar setar para o final toda vez que recarregar para uma nova página

//        if (isReloadPage) {
//            lvRepositories.setSelection(adapter.count - 1);
//        }
    }

    private fun callRepositoriesRequest() {
        val repositoryPresenterData = RepositoriesPresenterData(this)
        repositoryPresenterData.getRepositories(this.currentPage)
    }

    override fun onScroll(p0: AbsListView?, firstItemVisible: Int, visibleItemCounting: Int, totalItemCount: Int) {
//        if (loading) {
//            if (totalItemCount > previousTotal) {
//                loading = false
//                previousTotal = totalItemCount
//                currentPage ++
//            }
//        }
//
//        if (!loading && (totalItemCount - visibleItemCounting) <= (firstItemVisible - visibleThreshold)) {
//            callRepositoriesRequest()
//            loading = true
//        }

        Log.d("Teste", "TESTE")

        if ((firstItemVisible+visibleItemCounting) == totalItemCount) {
            currentPage += 1
            if (currentPage > 1) {
//                isReloadPage = true
            }
            callRepositoriesRequest()
        }


    }

    override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
        showMessageToast(this, R.string.msg_click, true)
    }


}
package br.com.tembici.desafio.view.activities.pull.requests

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import br.com.tembici.desafio.R
import br.com.tembici.desafio.model.PullRequest
import br.com.tembici.desafio.network.Connection
import br.com.tembici.desafio.presenter.data.PullRequestsPresenterData
import br.com.tembici.desafio.presenter.view.PullRequestsPresenterView
import br.com.tembici.desafio.view.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_pull_requests.*
import retrofit2.Response

/**
 * @author Daniel Monteiro
 *
 * @since 25/03/2020
 *
 */

class PullRequestsActivities : BaseActivity(), PullRequestsPresenterView.ViewReturnPullRequests {
    var loginUser = ""
    var repositoryName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        setUpInicialConfigActivity()

        loginUser = intent.getStringExtra("loginUser").toString()
        repositoryName = intent.getStringExtra("repositoryName").toString()

        supportActionBar!!.title = repositoryName
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        callPullRequests()

        lvPullRequests.setOnItemClickListener { adapterView, view, i, l ->
            val itemPullRequest = adapterView!!.getItemAtPosition(i) as PullRequest

            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(itemPullRequest.html_url)
            startActivity(openURL)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAdapterList(listItems: ArrayList<PullRequest>) {
        val adapter = PullRequestsAdapter(this, listItems)
        lvPullRequests.adapter = adapter
    }


    private fun callPullRequests() {
        val pullRequestPresenterData = PullRequestsPresenterData(this)
        pullRequestPresenterData.getPullRequests(loginUser, repositoryName)
    }

    override fun showProgressLoading() {
        showProgressDialog()
    }

    override fun hideProgressLoading() {
        dismissProgressDailog()
    }

    override fun onSuccess(responseList: Response<ArrayList<PullRequest>>) {
        setAdapterList(responseList.body()!!)
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
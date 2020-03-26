package br.com.tembici.desafio.view.activities.pull.requests

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.tembici.desafio.R
import br.com.tembici.desafio.model.PullRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pull_request.view.*

/**
 * @author Daniel Monteiro
 *
 * @since on 25/03/2020
 *
 * */


class PullRequestsAdapter(var activity: Activity, var pullRequests: ArrayList<PullRequest>): BaseAdapter() {

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View?
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        view = inflater.inflate(R.layout.item_pull_request, null)

        val pullRequest = pullRequests[p0]

        Picasso.get().load(pullRequest.user!!.avatar_url).into(view!!.civImageUser)

        view.tvUsername.text = pullRequest.user!!.login
        view.tvTitlePullRequest.text = pullRequest.title
        view.tvBodyPullRequest.text = pullRequest.body
        view.tvDatePullRequest.text = pullRequest.created_at

        return view
    }

    override fun getItem(p0: Int): Any {
        return this.pullRequests[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return this.pullRequests.size
    }


}
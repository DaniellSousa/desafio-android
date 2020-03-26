package br.com.tembici.desafio.view.activities.pull.requests

import android.os.Bundle
import br.com.tembici.desafio.R
import br.com.tembici.desafio.view.utils.BaseActivity

/**
 * @author Daniel Monteiro
 *
 * @since 25/03/2020
 *
 */

class PullRequestsActivities : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        setUpInicialConfigActivity()

    }
}
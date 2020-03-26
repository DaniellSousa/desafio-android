package br.com.tembici.desafio.view.activities.repositories

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.tembici.desafio.R
import br.com.tembici.desafio.model.ItemRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repository.view.*

/**
 * @author Daniel Monteiro
 *
 * @since on 25/03/2020
 *
 * */

class RepositoriesAdapter(var activity: Activity, var repositories: ArrayList<ItemRepository>): BaseAdapter() {

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View?
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        view = inflater.inflate(R.layout.item_repository, null)

        val repository = repositories[p0]

        Picasso.get().load(repository.owner!!.avatar_url).into(view!!.civImageUser)

        view.tvNameRepository.text = repository.name
        view.tvDescriptionRepository.text = repository.description
        view.tvDateRepository.text = repository.created_at

        view.tvUsername.text = repository.owner!!.login
        view.tvFullName.text = repository.full_name

        val forksAndStars = "Forks: " + repository.forks + "; Stars: " + repository.stargazers_count

        view.tvForksAndStars.text = forksAndStars

        return view
    }

    override fun getItem(p0: Int): Any {
        return repositories[p0]
    }

    override fun getItemId(p0: Int): Long {
        return repositories[p0].id!!.toLong()
    }

    override fun getCount(): Int {
        return repositories.size
    }


}
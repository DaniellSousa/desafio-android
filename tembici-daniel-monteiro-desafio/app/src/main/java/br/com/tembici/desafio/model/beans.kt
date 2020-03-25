package br.com.tembici.desafio.model

/**
 * @author Daniel Monteiro
 *
 * @since on 25/03/2020
 *
*/

data class RepositoriesReturn (
    var total_count: Int? = 0,
    var items: ArrayList<ItemRepository>
){}

data class ItemRepository (
    var id: String? = "",
    var name: String? = "",
    var full_name: String? = "",
    var forks: Int? = 0,
    var stargazers_count: Int? = 0,
    var owner: OwnerRepository?
){}

data class OwnerRepository (
    var login: String? = "",
    var id: Int? = 0,
    var avatar_url: String? = "",
    var created_at: String? = "",
    var description: String? = ""
) {}

data class PullRequest (
    var title: String? = "",
    var body: String? = "",
    var url: String? = "",
    var base: BasePullRequest?,
    var user: UserPullRequest?,
    var created_at: String? = ""
){}

data class UserPullRequest(
    var login: String? = "",
    var avatar_url: String? = ""
){}

data class BasePullRequest(
    var open_issues_count: Int? = 0
)

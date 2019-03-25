package io.arunbuilds.yagc_di.GithubApi

import io.arunbuilds.yagc_di.Models.RepoModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ReposApi {
@GET("repositories")
fun getRepoFromRemote():Observable<MutableList<RepoModel>>

}
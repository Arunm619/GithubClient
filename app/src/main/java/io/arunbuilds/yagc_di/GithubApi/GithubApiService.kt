package io.arunbuilds.yagc_di.GithubApi

import com.google.gson.Gson
import io.arunbuilds.yagc_di.Models.RepoModel
import io.reactivex.Observable
import retrofit2.Retrofit

class GithubApiService(val gson: Gson, val retrofit: Retrofit) {

    public fun getRepofromRemote(): Observable<MutableList<RepoModel>> {
        //creating instance of that interface using retrofit
        val reposApi = retrofit.create(ReposApi::class.java)
        //getting list of repos as observable
        return reposApi.getRepoFromRemote()
    }

}
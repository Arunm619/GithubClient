package io.arunbuilds.yagc_di.Root

import android.app.Application
import com.google.gson.Gson
import io.arunbuilds.yagc_di.GithubApi.GithubApiService
import retrofit2.Retrofit

class App : Application(){

  lateinit var githubApiService :GithubApiService

    override fun onCreate() {
        super.onCreate()
    }

    public fun getGithubApiService(gson: Gson,retrofit: Retrofit) : GithubApiService{
        githubApiService = GithubApiService(gson = gson,retrofit = retrofit)
        return githubApiService
    }
}
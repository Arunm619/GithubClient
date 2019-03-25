package io.arunbuilds.yagc_di.Root

import android.app.Application
import io.arunbuilds.yagc_di.GithubApi.GithubApiService

class App : Application() {
     lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
    }

    fun getGithubApiService(): GithubApiService {
      return appComponent.getGithubApiService()
    }
}
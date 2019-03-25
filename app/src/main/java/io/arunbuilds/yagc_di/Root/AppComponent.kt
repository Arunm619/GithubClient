package io.arunbuilds.yagc_di.Root

import dagger.Component
import io.arunbuilds.yagc_di.GithubApi.GithubApiService

@Component (modules = [AppModule::class])
interface AppComponent {
    fun getGithubApiService():GithubApiService
}
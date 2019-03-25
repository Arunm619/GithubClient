package io.arunbuilds.yagc_di.Root

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.arunbuilds.yagc_di.GithubApi.GithubApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    fun provideGson():Gson{
        return GsonBuilder().setLenient().create()
    }


    @Provides
    fun provideRetrofit(gson: Gson):Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGitHubApiService(gson: Gson, retrofit: Retrofit): GithubApiService{
        return GithubApiService(gson,retrofit)
    }


}
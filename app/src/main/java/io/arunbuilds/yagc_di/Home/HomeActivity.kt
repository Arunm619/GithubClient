package io.arunbuilds.yagc_di.Home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.arunbuilds.yagc_di.GithubApi.ReposApi
import io.arunbuilds.yagc_di.Models.RepoModel
import io.arunbuilds.yagc_di.R
import io.arunbuilds.yagc_di.ReposAdapter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : AppCompatActivity() {


    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var reposDataList: MutableList<RepoModel>
    lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //initialisation
        linearLayoutManager = LinearLayoutManager(this)
        reposDataList = arrayListOf()
        adapter = ReposAdapter(reposDataList, mContext = this)

        //setting adapter and llm
        rv_list.adapter = adapter
        rv_list.layoutManager = linearLayoutManager

        //creating gson
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        //creating retrofit
        val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        //creating instance of that interface using retrofit
        val reposApi = retrofit.create(ReposApi::class.java)
        //getting list of repos as observable
        val observableRepo : Observable<MutableList<RepoModel>> = reposApi.getRepoFromRemote()


        observableRepo.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object  : Observer<MutableList<RepoModel>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: MutableList<RepoModel>) {
                    adapter.addList(t)
                    adapter.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                }

            })
    }
}

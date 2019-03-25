package io.arunbuilds.yagc_di.Home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.arunbuilds.yagc_di.Models.RepoModel
import io.arunbuilds.yagc_di.R
import io.arunbuilds.yagc_di.ReposAdapter
import io.arunbuilds.yagc_di.Root.App
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*

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


        val app = application as App

        val githubApiService = app.getGithubApiService()

        githubApiService.getRepofromRemote().subscribeOn(Schedulers.io())
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

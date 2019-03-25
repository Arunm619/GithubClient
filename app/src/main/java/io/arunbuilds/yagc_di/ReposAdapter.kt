package io.arunbuilds.yagc_di

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import io.arunbuilds.yagc_di.Models.RepoModel

class ReposAdapter(private var list: MutableList<RepoModel>, private val mContext: Context) :
    RecyclerView.Adapter<ReposViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, p0: Int): ReposViewHolder {

        val itemView = inflater.inflate(R.layout.repo_list, parent, false)
        return ReposViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {


        //Glide.with(mContext).load(list[position].actor.avatar_url).into(holder.iv_profile)
        holder.tv_username.text = list[position].name

        holder.tvContent.text = list[position].description



        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View, position: Int, isLongClick: Boolean) {

                if (!isLongClick) {

                    val url = list[position].url
                    try {
                        val builder = CustomTabsIntent.Builder()
                        builder.setToolbarColor(
                            ContextCompat.getColor(
                                mContext,
                                R.color.colorPrimaryDark
                            )
                        )
                        val customTabsIntent = builder.build()
                        customTabsIntent.launchUrl(mContext, Uri.parse(url))
                    } catch (e: ActivityNotFoundException) {
                        e.printStackTrace()
                        Toast.makeText(mContext, mContext.getString(R.string.nobrowserdescription), Toast.LENGTH_LONG)
                            .show()
                    }

                }
            }
        })


    }

    fun addList(e: MutableList<RepoModel>){
        for(i in e)
            add(i)
    }

    fun add(e : RepoModel) {
        list.add(e)
    }


}

interface ItemClickListener {
    fun onClick(view: View, position: Int, isLongClick: Boolean)
}

class ReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
    View.OnLongClickListener {

    var tv_username: TextView = itemView.findViewById(R.id.tv_title)

    var tvContent: TextView = itemView.findViewById(R.id.tv_content)

    var iv_profile: ImageView = itemView.findViewById(R.id.iv_profile)

    private var itemClickListener: ItemClickListener? = null


    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    init {

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }


    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v!!, adapterPosition, false)

    }

    override fun onLongClick(v: View?): Boolean {
        itemClickListener!!.onClick(v!!, adapterPosition, true)
        return true
    }


}
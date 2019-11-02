package com.example.myapplication

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.PostLayoutBinding

class PostAdapter(val listner :View.OnClickListener,val list:ArrayList<PostModel>):RecyclerView.Adapter<PostAdapter.PostViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        val binding = PostLayoutBinding.inflate(inflater)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        p0.bind(list[p1])
        p0.attachListener(listner)
    }

    class PostViewHolder(val binding: PostLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(post:PostModel){
            binding.postName.text = post.postName
            binding.image.setImageURI(Uri.parse(post.postImageURL))
        }
        fun attachListener(listener: View.OnClickListener){
            binding.root.setOnClickListener(listener)
        }

    }
}
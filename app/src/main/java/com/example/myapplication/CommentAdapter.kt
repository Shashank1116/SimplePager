package com.example.myapplication

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.databinding.CommentsLayoutBinding

class CommentAdapter(val list: List<CommentModel>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val binding = CommentsLayoutBinding.inflate(layoutInflater)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: CommentViewHolder, p1: Int) {
        p0.bind(list[p1])
    }

    class CommentViewHolder(val binding: CommentsLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(commentModel:CommentModel){
            binding.userPhoto.setImageURI(Uri.parse(commentModel.userImageURL))
            binding.userName.text = commentModel.userName
            binding.comment.text = commentModel.userComment
        }

    }
}
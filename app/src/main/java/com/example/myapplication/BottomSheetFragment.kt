package com.example.myapplication

import android.app.Dialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.CommentsPopupBinding

class BottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = BottomSheetFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }

    lateinit var binding: CommentsPopupBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CommentAdapter
    private lateinit var dialog: BottomSheetDialog
    private lateinit var behavior: BottomSheetBehavior<View>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val sheet = d.findViewById<View>(android.support.design.R.id.design_bottom_sheet)
            behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = false
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.comments_popup, container, false)
        configureRecyclerView()
        populate()
        return binding.root
    }
    fun configureRecyclerView(){
        binding.commentsListView.scrollToPosition(0)
        binding.commentsListView.setHasFixedSize(true)
        binding.commentsListView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)

    }

    fun populate(){
        binding.commentsListView.adapter = CommentAdapter(getComments())
    }

    fun getComments():List<CommentModel>{
        val list = ArrayList<CommentModel>()
        list.add(CommentModel(1,"https://thumbor.forbes.com/thumbor/1280x868/https%3A%2F%2Fspecials-images.forbesimg.com%2Fdam%2Fimageserve%2F42977075%2F960x0.jpg","Donald Trump","I dont care man!!"))
        list.add(CommentModel(2,"https://static.independent.co.uk/s3fs-public/thumbnails/image/2018/03/18/15/billgates.jpg","Bill Gates","Curing Polio in India!"))
        return list

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
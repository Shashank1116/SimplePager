package com.example.myapplication

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.facebook.drawee.backends.pipeline.Fresco

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var listView:RecyclerView?=null
    private var dialog:BottomSheetDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.post_view)
        configureRecyclerView()
        populate()


    }

    fun configureRecyclerView(){
        listView!!.scrollToPosition(0)
        listView!!.setHasFixedSize(true)
        listView!!.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

    }

    fun populate(){
        listView!!.adapter = PostAdapter(object :View.OnClickListener{
            override fun onClick(v: View?) {
               showComments()
            }

        },getPosts())
        listView!!.adapter!!.notifyDataSetChanged()
    }

    fun getPosts():ArrayList<PostModel>{
        val list = ArrayList<PostModel>()
        list.add(PostModel(1,"Hello","https://image.cnbcfm.com/api/v1/image/104300870-steve_jobs.jpg?v=1532563905&w=1400&h=950"))
        list.add(PostModel(2,"How you doing?","https://image.cnbcfm.com/api/v1/image/104300870-steve_jobs.jpg?v=1532563905&w=1400&h=950"))
        return list
    }

    fun initCommentsView(){
        val modalbottomsheet = layoutInflater.inflate(R.layout.comments_popup, null)

        dialog = BottomSheetDialog(this)
        dialog!!.setContentView(modalbottomsheet)
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setCancelable(false)
    }

    fun showComments(){
        val fragment = BottomSheetFragment()
        fragment.show(supportFragmentManager,"BTM_ST")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

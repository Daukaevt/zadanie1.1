package com.wixsite.mupbam1.resume.zadanie11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wixsite.mupbam1.resume.zadanie11.databinding.ActivityMainBinding
import java.net.URL
import kotlin.concurrent.thread
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val tags = ArrayList<String>()
    val url="https://dev-tasks.alef.im/task-m-001/list.php"
    lateinit var spanCount:String
    var spanCount1 by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        spanCount = getString(R.string.span)
        spanCount1=spanCount.toInt()
        val swipe:SwipeRefreshLayout=binding.swipeRefreshLayout
        swipe.setOnRefreshListener {
            dataURL()
        }
        dataURL()
    }

    fun dataURL() {
        thread {
            val apiResponse = URL(url).readText()
            val replace = apiResponse.replace("", "")
            val dropStart = replace.drop(1)
            val dropEnd = dropStart.dropLast(1)
            var http=dropEnd.split(",")
            val size=http.size

            for (i in 0..size-1){
                var httpDrop= http[i].drop(1)
                var  httpDropLast=  httpDrop.dropLast(1)
                tags.add(httpDropLast)
            }

           runOnUiThread(Runnable {
               createList()
           })
        }
    }

    fun createList(){
        binding.tagsRV.layoutManager = GridLayoutManager(this@MainActivity, spanCount1)
        val adapter = TagsAdapter(this, tags, object : MyOnClickListener{
            override fun onClicked(tag: String) {
                val intent=Intent(this@MainActivity,FullScreenPic::class.java)
                    .apply {
                        putExtra(DialogConst.INTENT, tag)
                    }
                startActivity(intent)
            }
        })
        binding.tagsRV.adapter = adapter
    }
}
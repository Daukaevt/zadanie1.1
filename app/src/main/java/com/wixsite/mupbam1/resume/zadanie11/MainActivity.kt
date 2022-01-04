package com.wixsite.mupbam1.resume.zadanie11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
        Log.d("MyLog","spanCount1-$spanCount1")

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
     //       Log.d("MyLog","url*$tags, position[$size]")
           runOnUiThread(Runnable {
               createList()
           })
        }
    }


    fun createList(){

        binding.tagsRV.layoutManager = GridLayoutManager(this@MainActivity, spanCount1)
//        val linearLayoutManager = LinearLayoutManager(applicationContext)
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
//        binding.tagsRV.layoutManager = linearLayoutManager
      //  val tags = ArrayList<String>()
//        for (i in 0..tags.size-1){
//            Log.d("MyLog","tags[${tags[i]}]")
//        }

       // tags.add("tag1")
      //  tags.add("tag2")
      //  tags.add("https://madmikesamerica.com/wp-content/uploads/2011/04/arctic_fox.jpg")
      //tags.add("https://images.fineartamerica.com/images-medium-large-5/1-arctic-fox-pouncing-on-prey-arctic-steven-j-kazlowski--ghg.jpg")
        val adapter = TagsAdapter(this, tags, object : MyOnClickListener{
            override fun onClicked(tag: String) {
                Log.d("MyLog", "tag = " + tag)
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
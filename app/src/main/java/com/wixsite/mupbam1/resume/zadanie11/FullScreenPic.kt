package com.wixsite.mupbam1.resume.zadanie11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.wixsite.mupbam1.resume.zadanie11.databinding.ActivityFullScreenPicBinding

class FullScreenPic : AppCompatActivity() {
    lateinit var binding: ActivityFullScreenPicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityFullScreenPicBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getMainIntent()
    }
    private fun getMainIntent() {
        var i=intent
        if (i!=null){
            var Intent=i.getCharSequenceExtra(DialogConst.INTENT)
            var tagInt= Intent.toString()
            tagGlide(tagInt)
        }
    }

    private fun tagGlide(tagInt: String) {
        Glide.with(this).load(tagInt).into(binding.ivPic)

    }


}
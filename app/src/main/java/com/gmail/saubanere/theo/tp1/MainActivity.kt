package com.gmail.saubanere.theo.tp1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var imageView: ImageView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image_view)
        button = findViewById(R.id.my_button)
        button.setOnClickListener(this)
        loadImage("https://cdn.futura-sciences.com/buildsv6/images/wide1920/7/a/0/7a0ae575e0_132287_vague.jpg")
    }

    private fun loadImage(url: String) {
        Glide
            .with(this)
            .load(url)
            .override(900, 900)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_background)
            .into(imageView)
    }

    override fun onClick(v: View?) {
        val links = listOf(
            "https://www.presse-citron.net/app/uploads/2018/11/meilleure-banque-image.jpg",
            "https://img-19.ccm2.net/cI8qqj-finfDcmx6jMK6Vr-krEw=/1500x/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg",
            "https://img-19.ccm2.net/AINHgQU6hzAaA-eacqk4lYu9IhE=/1500x/smart/d8c10e7fd21a485c909a5b4c5d99e611/ccmcms-commentcamarche/20456790.jpg"
        )
        loadImage(links.random())
        Toast.makeText(this, "Vous m'avez cliqué", Toast.LENGTH_LONG).show()
    }
}

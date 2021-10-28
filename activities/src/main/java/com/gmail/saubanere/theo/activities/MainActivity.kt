package com.gmail.saubanere.theo.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var textView: TextView
    private var nbClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1 = findViewById(R.id.my_button)
        button1.setOnClickListener(this)
        button2 = findViewById(R.id.btn_compute)
        button2.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivities::class.java)
            startActivity(intent)
        }
        textView = findViewById(R.id.nbr_clics)
    }

    override fun onClick(v: View?) {
        nbClick++
        if (nbClick >= 5) {
            button1.isEnabled = false
        }
        val newText = "Vous avez cliqué $nbClick fois"
        textView.text = newText
    }
}

package com.gmail.saubanere.theo.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.saubanere.theo.activities.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var nbClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            myButton.setOnClickListener(this@MainActivity)
            btnCompute.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.my_button -> {
                nbClick++
                with(binding) {
                    myButton.isEnabled = nbClick <= 5
                    nbrClics.text = getString(R.string.nb_clic, nbClick)
                }
            }

            R.id.btn_compute -> {
                val intent = Intent(baseContext, ComputeActivities::class.java)
                startActivity(intent)
            }
        }
    }
}

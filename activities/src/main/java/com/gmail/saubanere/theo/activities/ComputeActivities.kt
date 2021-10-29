package com.gmail.saubanere.theo.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.saubanere.theo.activities.databinding.ComputeActivityBinding

class ComputeActivities : AppCompatActivity(), View.OnClickListener, TextWatcher {
    private lateinit var binding: ComputeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ComputeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculer.setOnClickListener(this)
        binding.btnCalculer.isEnabled = false
        binding.field1.addTextChangedListener(this)
        binding.field2.addTextChangedListener(this)
    }

    override fun onClick(v: View?) {
        with(binding) {
            resultat.text = (field1.text.toString().toDouble() + field2.text.toString().toDouble()).toString()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        with(binding) {
            btnCalculer.isEnabled = field1.text!!.isNotBlank() && field2.text!!.isNotBlank()
        }
    }
}

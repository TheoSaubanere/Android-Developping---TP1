package com.gmail.saubanere.theo.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ComputeActivities : AppCompatActivity(), View.OnClickListener, TextWatcher {
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)
        editText1 = findViewById(R.id.field_1)
        editText2 = findViewById(R.id.field_2)
        button = findViewById(R.id.btn_calculer)
        textView = findViewById(R.id.resultat)
        button.setOnClickListener(this)
        button.isEnabled = false
        editText1.addTextChangedListener(this)
        editText2.addTextChangedListener(this)
    }

    override fun onClick(v: View?) {
        val number1 = editText1.text.toString().toDouble()
        val number2 = editText2.text.toString().toDouble()
        val resultat = number1 + number2
        textView.text = resultat.toString()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        button.isEnabled = editText1.text.isNotBlank() && editText2.text.isNotBlank()
    }
}

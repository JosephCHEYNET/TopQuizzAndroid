package com.example.topquizzandroid.controler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.text.Editable
import android.text.TextWatcher
import android.content.Intent
import com.example.topquizzandroid.R
import com.example.topquizzandroid.model.User


class MainActivity : AppCompatActivity() {
    private lateinit var greetingTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var playButton: Button
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        greetingTextView = findViewById(R.id.main_textview_greeting)
        nameEditText = findViewById(R.id.main_edittext_name)
        playButton = findViewById(R.id.main_button_play)
        playButton.isEnabled = false
        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                playButton.isEnabled = s.toString().isNotEmpty()
            }
        })
        playButton.setOnClickListener {
            user = User(nameEditText.text.toString())
            val gameActivityIntent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(gameActivityIntent)
        }

    }
}
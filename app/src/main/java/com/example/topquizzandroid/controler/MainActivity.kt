package com.example.topquizzandroid.controler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.content.Intent
import com.example.topquizzandroid.R
import com.example.topquizzandroid.model.User


class MainActivity : AppCompatActivity() {
    private lateinit var mGreetingTextView: TextView
    private lateinit var mNameEditText: EditText
    private lateinit var mPlayButton: Button
    public lateinit var mUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mGreetingTextView = findViewById(R.id.main_textview_greeting)
        mNameEditText = findViewById(R.id.main_edittext_name)
        mPlayButton = findViewById(R.id.main_button_play)
        mPlayButton.isEnabled = false
        mNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                mPlayButton.setEnabled(!s.toString().isEmpty());
            }
        })
        mPlayButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mUser.firstName = mNameEditText.text.toString()
                val gameActivityIntent = Intent(this@MainActivity, GameActivity::class.java)
                startActivity(gameActivityIntent)
                // The user just clicked
            }
        })

    }
}
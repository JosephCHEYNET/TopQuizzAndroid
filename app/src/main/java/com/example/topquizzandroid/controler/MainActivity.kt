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
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var greetingTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var playButton: Button
    private lateinit var user: User
    private var score: Int? = 0
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
        user = User(getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, "")
            .toString())

        val score = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(
            SHARED_PREF_USER_INFO_SCORE,
            -1
        )
        if (user.firstName.isNotEmpty()){
            if (score != -1) {
                greetingTextView.text =getString(R.string.welcome_back_with_score, user.firstName, score)
            } else {
                greetingTextView.text = getString(R.string.welcome_back, user.firstName)
            }
            greetingTextView.text = ""
            nameEditText.setText(user.firstName)
        }
        playButton.setOnClickListener {
            user.firstName = nameEditText.text.toString()
            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                .edit()
                .putString(SHARED_PREF_USER_INFO_NAME, user.firstName)
                .apply()
            val gameActivityIntent = Intent(this@MainActivity, GameActivity::class.java)
            startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            score = data?.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE,0)
            displayScore()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun displayScore(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Well done ${user.firstName}!")
                .setMessage("Your score is $score")
                .setPositiveButton("OK") { _, _ -> finish() }
                .create()
                .show()
    }

    companion object {
        private const val GAME_ACTIVITY_REQUEST_CODE = 1
        private const val SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO"
        private const val SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME"
        private const val SHARED_PREF_USER_INFO_SCORE = "SHARED_PREF_USER_INFO_SCORE"
    }
}
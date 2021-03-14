package com.example.recyclerviewretrofit

import android.app.StatusBarManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        circleImageView.animate().apply {
            duration = 1000
            rotationYBy(360f)
        }.start()

        textView.animate().apply {
            duration = 1000
            rotationYBy(-360f)
        }.start()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}
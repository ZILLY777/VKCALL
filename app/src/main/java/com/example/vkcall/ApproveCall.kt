package com.example.vkcall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast

class ApproveCall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_approve_call)
        val approveCall = findViewById<ImageButton>(R.id.approveCallButton)
        val cancelCall = findViewById<ImageButton>(R.id.cancellCallButton)

        approveCall.setOnClickListener {
            val intent = Intent(this@ApproveCall, MainActivity::class.java)
            startActivity(intent)
        }
        cancelCall.setOnClickListener {
            finish()
        }
    }


}
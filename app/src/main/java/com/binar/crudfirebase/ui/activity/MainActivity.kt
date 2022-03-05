package com.binar.crudfirebase.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binar.crudfirebase.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.notes)
    }
}


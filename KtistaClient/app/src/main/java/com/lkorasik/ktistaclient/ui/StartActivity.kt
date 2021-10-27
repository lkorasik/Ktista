package com.lkorasik.ktistaclient.ui

import android.os.*
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.lkorasik.ktistaclient.R

class StartActivity: AppCompatActivity() {
    private lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_start)

        fragmentContainer = findViewById(R.id.fragment_container)
    }
}
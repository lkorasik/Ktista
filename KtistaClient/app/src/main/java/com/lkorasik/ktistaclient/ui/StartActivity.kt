package com.lkorasik.ktistaclient.ui

import android.os.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityStartBinding

class StartActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentContainer = findViewById(R.id.fragment_container)
    }
}
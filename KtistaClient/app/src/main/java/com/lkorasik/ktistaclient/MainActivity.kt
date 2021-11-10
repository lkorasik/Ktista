package com.lkorasik.ktistaclient

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lkorasik.ktistaclient.changepassword.*
import com.lkorasik.ktistaclient.databinding.ActivityMainBinding
import com.lkorasik.ktistaclient.ui.AddPostActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_feed, R.id.navigation_profile, R.id.navigation_settings
            )
        )
        navView.setupWithNavController(navController)

       // startActivity(Intent(this, AddPostActivity::class.java))

        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
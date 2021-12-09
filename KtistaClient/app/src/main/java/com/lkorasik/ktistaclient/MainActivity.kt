package com.lkorasik.ktistaclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lkorasik.ktistaclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.includeToolbar.customToolbar

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.navView


        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.navigation_feed, R.id.navigation_profile, R.id.navigation_settings,
            ),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp,
        )
        setSupportActionBar(toolbar)
        navView.setupWithNavController(navController)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}

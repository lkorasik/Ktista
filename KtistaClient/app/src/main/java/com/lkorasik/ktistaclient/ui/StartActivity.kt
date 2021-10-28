package com.lkorasik.ktistaclient.ui

import android.os.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityStartBinding
import com.lkorasik.ktistaclient.ui.login.LoginFragment

class StartActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private val fragmentContainerId = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(fragmentContainerId, LoginFragment())
        transaction.commit()
    }

    private fun initFragment(){
        setTheme(R.style.Theme_Ktista_NoActionBar)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
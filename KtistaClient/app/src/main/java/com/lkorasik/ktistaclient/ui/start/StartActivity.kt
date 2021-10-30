package com.lkorasik.ktistaclient.ui.start

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityStartBinding
import com.lkorasik.ktistaclient.ui.start.login.LoginFragment

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
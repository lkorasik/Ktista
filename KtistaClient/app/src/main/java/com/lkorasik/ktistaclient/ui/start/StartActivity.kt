package com.lkorasik.ktistaclient.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lkorasik.ktistaclient.MainActivity
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityStartBinding
import com.lkorasik.ktistaclient.ui.start.login.LoginFragment
import com.lkorasik.ktistaclient.ui.start.registration.RegistrationFragment

class StartActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private val fragmentContainerId = R.id.fragment_container

    private lateinit var registrationLoginFragment: RegistrationFragment
    private lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()

        registrationLoginFragment = RegistrationFragment()
        loginFragment = LoginFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(fragmentContainerId, loginFragment)
        transaction.commit()
    }

    fun showRegistrationFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentContainerId, registrationLoginFragment).commit()
    }

    fun showLoginFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(fragmentContainerId, loginFragment).commit()
    }

    fun launchMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun initFragment(){
        setTheme(R.style.Theme_Ktista_NoActionBar)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.lkorasik.ktistaclient.ui.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lkorasik.ktistaclient.net.repository.UserRepository
import com.lkorasik.ktistaclient.ui.start.login.LoginViewModel
import com.lkorasik.ktistaclient.ui.start.registration.RegistrationViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass){
            LoginViewModel::class.java -> {
                LoginViewModel(UserRepository(context))
            }
            RegistrationViewModel::class.java -> {
                RegistrationViewModel(UserRepository(context))
            }
            else -> throw IllegalStateException("Unknown ViewModel class")
        }
        return viewModel as T
    }

}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext)

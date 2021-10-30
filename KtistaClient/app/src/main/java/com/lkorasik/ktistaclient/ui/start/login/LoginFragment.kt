package com.lkorasik.ktistaclient.ui.start.login

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lkorasik.ktistaclient.databinding.*
import com.lkorasik.ktistaclient.ui.start.StartActivity

class LoginFragment: Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private var bindingObject: FragmentLoginBinding? = null
    private lateinit var rootActivity: StartActivity

    private val binding get() = bindingObject!!

    private lateinit var nickname: EditText
    private lateinit var password: EditText
    private lateinit var signIn: Button
    private lateinit var signUp: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        bindingObject = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rootActivity = activity as StartActivity

        nickname = binding.nickname
        password = binding.password
        signIn = binding.signIn
        signUp = binding.signUp

        signIn.setOnClickListener {
            rootActivity.launchMainActivity()
        }
        signUp.setOnClickListener {
            rootActivity.showRegistrationFragment()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingObject = null
    }
}
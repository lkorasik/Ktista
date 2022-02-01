package com.lkorasik.ktistaclient.ui.start.login

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.FragmentLoginBinding
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.ui.start.StartActivity
import com.lkorasik.ktistaclient.ui.utils.ViewModelFactory

class LoginFragment : Fragment() {
    private lateinit var  loginViewModel: LoginViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var bindingObject: FragmentLoginBinding? = null
    private lateinit var rootActivity: StartActivity

    private val binding
        get() = bindingObject
            ?: throw IllegalStateException("Try use binding before onCreateView or after onDestroyView")

    private lateinit var nickname: EditText
    private lateinit var password: EditText
    private lateinit var signIn: Button
    private lateinit var signUp: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelFactory = ViewModelFactory(requireContext().applicationContext)
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        loginViewModel.inProgress.observe(viewLifecycleOwner) {
            Log.i(LoginViewModel.LOG_TAG, "$it")

            if (it.equals(RequestStages.SUCCESS)) {
                signIn.hideProgress("Success!")
                rootActivity.launchMainActivity()
            }
            if (it.equals(RequestStages.FAIL)) {
                signIn.hideProgress("Fail!")
            }
            if (it.equals(RequestStages.IN_PROGRESS)) {
                signIn.showProgress {
                    buttonTextRes = R.string.registration_button_progress
                    progressColor = Color.WHITE
                }
            }
        }

        bindingObject = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rootActivity = activity as StartActivity

        nickname = binding.nickname
        password = binding.password
        signIn = binding.signIn
        signUp = binding.signUp

        bindProgressButton(signIn)

        signIn.setOnClickListener {
            loginViewModel.loginUser(nickname.text.toString(), password.text.toString())
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
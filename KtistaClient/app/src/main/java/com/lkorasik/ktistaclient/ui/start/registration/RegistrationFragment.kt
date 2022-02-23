package com.lkorasik.ktistaclient.ui.start.registration

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
import com.lkorasik.ktistaclient.databinding.FragmentRegistrationBinding
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.ui.start.StartActivity
import com.lkorasik.ktistaclient.ui.utils.ViewModelFactory

class RegistrationFragment: Fragment() {
    private lateinit var registrationViewModel: RegistrationViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private var bindingObject: FragmentRegistrationBinding? = null
    private lateinit var rootActivity: StartActivity

    private val binding get() = bindingObject ?: throw IllegalStateException("Try use binding before onCreateView or after onDestroyView")

    private lateinit var email: EditText
    private lateinit var nickname: EditText
    private lateinit var password: EditText
    private lateinit var signIn: Button
    //https://github.com/razir/ProgressButton
    private lateinit var signUp: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModelFactory = ViewModelFactory(requireContext().applicationContext)
        registrationViewModel = ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]

        registrationViewModel.inProgress.observe(viewLifecycleOwner) {
            Log.i(RegistrationViewModel.LOG_TAG, "$it")

            if (it.equals(RequestStages.SUCCESS)) {
                signUp.hideProgress("Success!")
                rootActivity.showLoginFragment()
            }
            if (it.equals(RequestStages.FAIL)) {
                signUp.hideProgress("Fail!")
            }
            if (it.equals(RequestStages.IN_PROGRESS)) {
                signUp.showProgress {
                    buttonTextRes = R.string.registration_button_progress
                    progressColor = Color.WHITE
                }
            }
        }

        bindingObject = FragmentRegistrationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rootActivity = activity as StartActivity

        nickname = binding.nickname
        email = binding.email
        password = binding.password
        signIn = binding.signIn
        signUp = binding.signUp

        bindProgressButton(signUp)

        signIn.setOnClickListener {
            rootActivity.showLoginFragment()
        }
        signUp.setOnClickListener {
            registrationViewModel.registerUser(nickname.text.toString(), password.text.toString(), email.text.toString())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingObject = null
    }
}

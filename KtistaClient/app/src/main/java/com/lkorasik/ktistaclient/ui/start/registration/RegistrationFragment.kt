package com.lkorasik.ktistaclient.ui.start.registration

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.lkorasik.ktistaclient.*
import com.lkorasik.ktistaclient.databinding.*
import com.lkorasik.ktistaclient.net.RegistrationStages
import com.lkorasik.ktistaclient.ui.start.StartActivity

class RegistrationFragment: Fragment() {
    private lateinit var registrationViewModel: RegistrationViewModel
    private var bindingObject: FragmentRegistrationBinding? = null
    private lateinit var rootActivity: StartActivity

    private val binding get() = bindingObject!!

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var nickname: EditText
    private lateinit var password: EditText
    private lateinit var signIn: Button
    //https://github.com/razir/ProgressButton
    private lateinit var signUp: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        registrationViewModel.inProgress.observe(this, {
            Log.i(RegistrationViewModel.LOG_TAG, "$it")

            if(it.equals(RegistrationStages.SUCCESS)) {
                signUp.hideProgress("Success!")
                rootActivity.launchMainActivity()
            }
            if(it.equals(RegistrationStages.FAIL)) {
                signUp.hideProgress("Fail!")
            }
            if(it.equals(RegistrationStages.IN_PROGRESS)){
                signUp.showProgress {
                    buttonTextRes = R.string.registration_button_progress
                    progressColor = Color.WHITE
                }
            }
        })

        bindingObject = FragmentRegistrationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rootActivity = activity as StartActivity

        firstName = binding.firstName
        lastName = binding.lastName
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
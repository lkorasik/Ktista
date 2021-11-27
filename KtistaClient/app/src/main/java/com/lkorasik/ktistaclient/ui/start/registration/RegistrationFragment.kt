package com.lkorasik.ktistaclient.ui.start.registration

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lkorasik.ktistaclient.*
import com.lkorasik.ktistaclient.databinding.*
import com.lkorasik.ktistaclient.net.Controller
import com.lkorasik.ktistaclient.net.model.UserRegistrationRequest
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
    private lateinit var signUp: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

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

        val controller = Controller(rootActivity.baseContext, { _ ->
            Toast.makeText(rootActivity.baseContext, "Success", Toast.LENGTH_SHORT).show()
            rootActivity.launchMainActivity()
        }, {
            Toast.makeText(rootActivity.baseContext, "Fail", Toast.LENGTH_SHORT).show()
        })

        signIn.setOnClickListener {
            rootActivity.showLoginFragment()
        }
        signUp.setOnClickListener {
            controller.registerUser(UserRegistrationRequest(nickname.text.toString(), password.text.toString(), email.text.toString()))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingObject = null
    }
}
package com.lkorasik.ktistaclient.ui.start.registration

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lkorasik.ktistaclient.databinding.*

class RegistrationFragment: Fragment() {
    private lateinit var registrationViewModel: RegistrationViewModel
    private var bindingObject: FragmentRegistrationBinding? = null

    private val binding get() = bindingObject!!

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var nickname: EditText
    private lateinit var password: EditText
    private lateinit var complete: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        bindingObject = FragmentRegistrationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        firstName = binding.firstName
        lastName = binding.lastName
        nickname = binding.nickname
        email = binding.email
        password = binding.password
        complete = binding.complete

        complete.setOnClickListener {
            Toast.makeText(root.context, "Sign in: ${firstName.text}\n${lastName.text}\n${nickname.text}\n${email.text}\n${password.text}", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingObject = null
    }
}
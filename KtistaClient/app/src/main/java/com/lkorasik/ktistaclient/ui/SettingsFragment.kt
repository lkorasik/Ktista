package com.lkorasik.ktistaclient.ui

import android.os.Bundle
import android.text.InputType
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.lkorasik.ktistaclient.R

class SettingsFragment: Fragment() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var nicknameEditText: EditText
    private lateinit var changePasswordButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        initViews(view)

        return view;
    }

    private fun initViews(view: View){
        initFirstNameView(view)
        initLastNameView(view)
        initEmailView(view)
        initNicknameView(view)
        initChangePasswordView(view)
    }

    private fun initFirstNameView(view: View){
        val root = view.findViewById<View>(R.id.first_name)

        root.findViewById<TextView>(R.id.field_title).apply {
            text = getString(R.string.settings_first_name)
        }
        firstNameEditText = root.findViewById<EditText>(R.id.field_value).apply{
            hint = getString(R.string.settings_first_name)
            inputType = InputType.TYPE_CLASS_TEXT
        }
    }

    private fun initLastNameView(view: View){
        val root = view.findViewById<View>(R.id.last_name)

        root.findViewById<TextView>(R.id.field_title).apply {
            text = getString(R.string.settings_last_name)
        }
        lastNameEditText = root.findViewById<EditText>(R.id.field_value).apply{
            hint = getString(R.string.settings_last_name)
            inputType = InputType.TYPE_CLASS_TEXT
        }
    }

    private fun initEmailView(view: View){
        val root = view.findViewById<View>(R.id.email)

        root.findViewById<TextView>(R.id.field_title).apply {
            text = getString(R.string.settings_email_name)
        }
        emailEditText = root.findViewById<EditText>(R.id.field_value).apply {
            hint = getString(R.string.settings_email_name)
            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
    }

    private fun initNicknameView(view: View){
        val root = view.findViewById<View>(R.id.nickname)

        root.findViewById<TextView>(R.id.field_title).apply{
            text = getString(R.string.settings_nickname_name)
        }
        nicknameEditText = root.findViewById<EditText>(R.id.field_value).apply {
            hint = getString(R.string.settings_nickname_name)
            inputType = InputType.TYPE_CLASS_TEXT
        }
    }

    private fun initChangePasswordView(view: View){
        val root = view.findViewById<View>(R.id.password)

        root.findViewById<TextView>(R.id.field_title).apply{
            text = getString(R.string.settings_password)
        }
        changePasswordButton = root.findViewById<Button>(R.id.button).apply {
            text = getString(R.string.settings_change)

            setOnClickListener {
                Toast.makeText(view.context, "Mock. I fill it in next task", Toast.LENGTH_LONG).show()
                //TODO("Realize change password")
            }
        }
    }
}
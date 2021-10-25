package com.lkorasik.ktistaclient.ui

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.lkorasik.ktistaclient.R

class SettingsFragment: Fragment() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var nicknameEditText: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        initViews(view)

        return view;
    }

    private fun initViews(view: View){
        var root = view.findViewById<View>(R.id.first_name)

        root.findViewById<TextView>(R.id.field_title).apply {
            text = "First name"
        }
        firstNameEditText = root.findViewById<EditText>(R.id.field_value).apply{
            hint = "Your first name"
        }

        root = view.findViewById(R.id.last_name)

        root.findViewById<TextView>(R.id.field_title).apply {
            text = "Last name"
        }
        lastNameEditText = root.findViewById<EditText>(R.id.field_value).apply{
            hint = "Your last name"
        }

        root = view.findViewById(R.id.email)

        root.findViewById<TextView>(R.id.field_title).apply {
            text = "Email"
        }
        emailEditText = root.findViewById<EditText>(R.id.field_value).apply {
            hint = "Your email"
        }

        root = view.findViewById(R.id.nickname)

        root.findViewById<TextView>(R.id.field_title).apply{
            text = "Nickname"
        }
        nicknameEditText = root.findViewById<EditText>(R.id.field_value).apply {
            hint = "Your nickname"
        }
    }
}
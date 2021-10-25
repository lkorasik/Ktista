package com.lkorasik.ktistaclient.ui

import android.content.Context
import android.opengl.ETC1
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.lkorasik.ktistaclient.R
import java.util.*

class SettingsFragment: Fragment() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var nicknameEditText: EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}
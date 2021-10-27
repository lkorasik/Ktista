package com.lkorasik.ktistaclient.changepassword

import android.app.*
import android.content.*
import android.text.InputType
import android.widget.*
import com.lkorasik.ktistaclient.R
import kotlin.collections.ArrayList

class ChangePasswordDialog(private val context: Context, private val activity: Activity) {
    private var currentPasswordEditText: EditText
    private var newPasswordEditText: EditText
    private var repeatNewPasswordEditText: EditText
    private var dialog: AlertDialog

    private val onCompleteListeners: ArrayList<OnCompleteListener<PasswordValues>> = arrayListOf()

    init {
        val dialogBuilder = AlertDialog.Builder(context)
        dialogBuilder.setTitle("Change password")

        val customLayout = activity.layoutInflater.inflate(R.layout.dialog_change_password, null)

        dialogBuilder.setView(customLayout)

        currentPasswordEditText = customLayout.findViewById<EditText>(R.id.current_password).apply {
            hint = "Current pswd"
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        newPasswordEditText = customLayout.findViewById<EditText>(R.id.new_password).apply {
            hint = "New pswd"
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        repeatNewPasswordEditText = customLayout.findViewById<EditText>(R.id.repeat_new_password).apply {
            hint = "Repeat new pswd"
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        dialogBuilder.setPositiveButton("Ok") { _, _ ->
            for(listener in onCompleteListeners)
                listener.onComplete(
                    PasswordValues(
                        currentPasswordEditText.text.toString(),
                        newPasswordEditText.text.toString(),
                        repeatNewPasswordEditText.text.toString()
                    )
                )
            Toast.makeText(context, "Changed!", Toast.LENGTH_LONG).show()
        }
        dialogBuilder.setNegativeButton("Cancel") { _, _ ->
            Toast.makeText(context, "Go back", Toast.LENGTH_LONG).show()
        }

        dialog = dialogBuilder.create()
    }

    /**
     * Call listener.onComplete after closing dialog
     */
    fun setOnCompleteListener(listener: OnCompleteListener<PasswordValues>){
        onCompleteListeners.add(listener)
    }

    /**
     * Call lambda after closing dialog
     */
    fun setOnCompleteListener(listener: (PasswordValues) -> Unit){
        val newListener = object: OnCompleteListener<PasswordValues> {
            override fun onComplete(container: PasswordValues) {
                listener(container)
            }
        }

        onCompleteListeners.add(newListener)
    }

    fun show() = dialog.show()
}

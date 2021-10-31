package com.lkorasik.ktistaclient.changepassword

import android.app.AlertDialog
import android.content.*
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.*
import com.lkorasik.ktistaclient.R

class NewChangePasswordDialog(context: Context) {
    private val ok: Button
    private val cancel: Button
    private val currentPassword: EditText
    private val newPassword: EditText
    private val repeatNewPassword: EditText

    private val dialog: AlertDialog

    private val onCompleteListeners: ArrayList<OnCompleteListener<PasswordValues>> = arrayListOf()

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_change_password, null)
        val builder = AlertDialog.Builder(context)

        builder.setView(view)
        dialog = builder.create()

        currentPassword = view.findViewById(R.id.current_password)
        newPassword = view.findViewById(R.id.new_password)
        repeatNewPassword = view.findViewById(R.id.repeat_new_password)

        // This line is necessary for rounded shape of dialog back
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        ok = view.findViewById(R.id.btn_ok)
        ok.setOnClickListener {
            dialog.dismiss()

            for(listener in onCompleteListeners)
                listener.onComplete(
                    PasswordValues(
                        currentPassword.text.toString(),
                        newPassword.text.toString(),
                        repeatNewPassword.text.toString()
                    ))
        }

        cancel = view.findViewById(R.id.btn_cancel)
        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun show(){
        dialog.create()
        dialog.show()
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
}
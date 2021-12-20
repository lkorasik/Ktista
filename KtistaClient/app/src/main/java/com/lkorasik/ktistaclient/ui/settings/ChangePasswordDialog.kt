package com.lkorasik.ktistaclient.ui.settings

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.lkorasik.ktistaclient.databinding.DialogChangePasswordBinding

class ChangePasswordDialog : DialogFragment() {

    private var _binding: DialogChangePasswordBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Try use binding before onCreateView or after onDestroyView")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogChangePasswordBinding.inflate(LayoutInflater.from(context))
        binding.btnOk.setOnClickListener {
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        val dialog = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
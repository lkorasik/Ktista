package com.lkorasik.ktistaclient.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.*
import com.lkorasik.ktistaclient.ui.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.userSubscribe.fuUserInfo.tvUserName.text = "Lev"
        binding.userSubscribe.fuUserInfo.tvNickname.text = "sololev"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
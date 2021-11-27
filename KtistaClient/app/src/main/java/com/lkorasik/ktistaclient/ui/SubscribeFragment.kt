package com.lkorasik.ktistaclient.ui

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.lkorasik.ktistaclient.databinding.*

class SubscribeFragment: Fragment() {
    private var bindingObject: FragmentSubscribeBinding? = null

    private val binding get() = bindingObject!!

    private lateinit var fragmentUser: FragmentUserBinding
    private lateinit var action: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindingObject = FragmentSubscribeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fragmentUser = binding.fuUserInfo
        action = binding.btnActionButton

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingObject = null
    }
}

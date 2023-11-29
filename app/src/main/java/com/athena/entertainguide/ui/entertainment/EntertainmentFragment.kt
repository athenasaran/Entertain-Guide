package com.athena.entertainguide.ui.entertainment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.athena.entertainguide.databinding.FragmentEntertainmentBinding
import com.athena.entertainguide.ui.base.BaseFragment

class EntertainmentFragment : BaseFragment<FragmentEntertainmentBinding>() {

    override val binding: FragmentEntertainmentBinding by lazy {
        FragmentEntertainmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    companion object {
        fun newInstance() = EntertainmentFragment()
    }
}
package com.athena.entertainguide.ui.initial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.FragmentInitialBinding
import com.athena.entertainguide.ui.BaseFragment

class InitialFragment : BaseFragment<FragmentInitialBinding>() {

    override val binding: FragmentInitialBinding by lazy {
        FragmentInitialBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInfoHeader()
    }

    private fun setupInfoHeader() = with(binding.header) {
        title = this@InitialFragment.getString(R.string.home)
        firstIconButton = R.drawable.ic_app
    }

    companion object {
        fun newInstance() = InitialFragment()
    }
}
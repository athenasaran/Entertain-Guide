package com.athena.entertainguide.ui.savelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.FragmentSaveListBinding
import com.athena.entertainguide.sharedPreferences.SharedPreferencesManager
import com.athena.entertainguide.sharedPreferences.keys.SharedPreferencesKeys
import com.athena.entertainguide.ui.base.BaseFragment
import com.athena.entertainguide.ui.login.LoginFragment
import org.koin.android.ext.android.inject

class SaveListFragment : BaseFragment<FragmentSaveListBinding>() {

    override val binding: FragmentSaveListBinding by lazy {
        FragmentSaveListBinding.inflate(layoutInflater)
    }

    private val sharedPreferences: SharedPreferencesManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verifyLoginUser()
        setupClickListener()
    }

    private fun verifyLoginUser() {
        val userLogged = sharedPreferences.getBoolean(SharedPreferencesKeys.LOGGED_USER.key)
        if (userLogged) {
            setVisibility(recyclerViewVisibility = true, groupVisibility = false)
        } else {
            setVisibility(recyclerViewVisibility = false, groupVisibility = true)
        }
    }

    private fun setVisibility(recyclerViewVisibility: Boolean, groupVisibility: Boolean) = with(binding) {
        rvSaveList.isVisible = recyclerViewVisibility
        grSaveListNotAvailable.isVisible = groupVisibility
    }

    private fun setupClickListener() {
        binding.btSignIn.setOnClickListener {
            gotoLoginFragment()
        }
    }

    private fun gotoLoginFragment() {
        val initialFragment = LoginFragment.newInstance()

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, initialFragment)
            .commit()
    }

    companion object {
        fun newInstance() = SaveListFragment()
    }
}
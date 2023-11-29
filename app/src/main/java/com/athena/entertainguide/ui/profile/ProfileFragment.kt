package com.athena.entertainguide.ui.profile

import android.os.Bundle
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.FragmentProfileBinding
import com.athena.entertainguide.sharedPreferences.SharedPreferencesManager
import com.athena.entertainguide.sharedPreferences.keys.SharedPreferencesKeys
import com.athena.entertainguide.ui.base.BaseFragment
import com.athena.entertainguide.ui.initial.InitialFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth
    private val sharedPreferences: SharedPreferencesManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListeners()
        auth = Firebase.auth
        setupUser()
    }

    private fun setupUser() {
        binding.tvNameUser.text = sharedPreferences.getString(SharedPreferencesKeys.EMAIL_USER.key)
    }

    private fun setupListeners() = with(binding) {
        btSignOut.setOnClickListener {
            auth.signOut()
            sharedPreferences.putBoolean(SharedPreferencesKeys.LOGGED_USER.key, false)
            gotoInitialFragment()
        }
    }

    private fun gotoInitialFragment() {
        val initialFragment = InitialFragment.newInstance()

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, initialFragment)
            .commit()
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}
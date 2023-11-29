package com.athena.entertainguide.ui.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.athena.entertainguide.R
import com.athena.entertainguide.databinding.FragmentLoginBinding
import com.athena.entertainguide.sharedPreferences.SharedPreferencesManager
import com.athena.entertainguide.sharedPreferences.keys.SharedPreferencesKeys
import com.athena.entertainguide.ui.base.BaseFragment
import com.athena.entertainguide.ui.initial.InitialFragment
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginWithGoogle.OnLoginListener {

    override val binding: FragmentLoginBinding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    private lateinit var loginWithGoogle: LoginWithGoogle
    private lateinit var auth: FirebaseAuth
    private val sharedPreferences: SharedPreferencesManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListeners()
        loginWithGoogle = LoginWithGoogle(this, this)
        auth = Firebase.auth
    }

    private fun setupListeners() = with(binding) {
        btGoogle.setOnClickListener {
            loginWithGoogle.signIn()
        }
        btLogin.setOnClickListener {
            setVisibilityLoading(true)
            signIn(etEmail.text.toString(), etPassword.text.toString())
        }
        btCreateAccount.setOnClickListener {
            createAccount(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    saveUserOnSharedPreferences(user)
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.user_created),
                        Toast.LENGTH_SHORT,
                    ).show()
                    setVisibilityLoading(false)
                } else {
                    binding.errorLogin.apply {
                        isVisible = true
                        text = getString(R.string.authentication_failed)
                    }
                    setVisibilityLoading(false)
                }
            }
    }

    private fun saveUserOnSharedPreferences(user: FirebaseUser?) {
        sharedPreferences.putBoolean(SharedPreferencesKeys.LOGGED_USER.key, true)
        sharedPreferences.putString(SharedPreferencesKeys.EMAIL_USER.key, user?.email.orEmpty())
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    saveUserOnSharedPreferences(user)
                    gotoInitialFragment()
                    setVisibilityLoading(false)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.authentication_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                    setVisibilityLoading(false)
                }
            }
    }

    private fun setVisibilityLoading(isVisible: Boolean) {
        binding.loading.isVisible = isVisible
    }

    override fun onLoginSuccess(account: GoogleSignInAccount) {
        Toast.makeText(requireContext(), account.displayName, Toast.LENGTH_LONG).show()
    }

    override fun onLoginFailed(e: ApiException) {
        Log.e("GoogleSignIn", "signInResult:failed", e)
    }

    private fun gotoInitialFragment() {
        val initialFragment = InitialFragment.newInstance()

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, initialFragment)
            .commit()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
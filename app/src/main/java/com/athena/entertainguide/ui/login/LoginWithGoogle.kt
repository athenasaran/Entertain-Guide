package com.athena.entertainguide.ui.login

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginWithGoogle(private val fragment: Fragment, private val onLoginListener: OnLoginListener) {

    private val signInResultLauncher: ActivityResultLauncher<Intent> = fragment.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data: Intent? = result.data
        if (data != null) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                onLoginListener.onLoginSuccess(account)
            } catch (e: ApiException) {
                onLoginListener.onLoginFailed(e)
            }
        }
    }

    private fun googleSignInOptions(): GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    private fun googleSignInClient(): GoogleSignInClient =
        GoogleSignIn.getClient(fragment.requireActivity(), googleSignInOptions())

    fun signIn() {
        val signInIntent = googleSignInClient().signInIntent
        signInResultLauncher.launch(signInIntent)
    }

    fun signOut() {
        googleSignInClient().signOut()
    }

    interface OnLoginListener {
        fun onLoginSuccess(account: GoogleSignInAccount)
        fun onLoginFailed(e: ApiException)
    }
}
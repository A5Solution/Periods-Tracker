package com.example.periodflow.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.periodflow.R
import com.example.periodflow.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureGoogleSignIn()
        binding.editTextTextPassword.setTextColor(Color.WHITE)
        binding.editTextTextPassword.background.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        binding.edtEmail.setTextColor(Color.WHITE)
        binding.edtEmail.background.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_languageFragmentFirst)
        }
        binding.btnContinueWithGoogle.setOnClickListener(){
            binding.btnContinueWithGoogle.setOnClickListener {
                // Start the sign-in process
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, Companion.RC_SIGN_IN)
            }
        }
        return binding.root
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, continue with app logic
            // You can now make the necessary Google APIs accessible to your app
            // For example, you might obtain an access token here
            // Then, navigate to the next destination
            findNavController().navigate(R.id.action_loginFragment_to_languageFragmentFirst)
        } catch (e: ApiException) {
            // Sign in failed, handle the error
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }
    private fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            // Add any additional sign-in options here as needed
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }

    companion object {
        private const val RC_SIGN_IN = 9001 // You can use any integer value you want here
    }
}
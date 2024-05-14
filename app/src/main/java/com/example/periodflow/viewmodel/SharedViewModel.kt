package com.example.periodflow.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SharedViewModel: ViewModel() {
    lateinit var auth: FirebaseAuth

    fun firebaseAuthIntialize(){
        auth = FirebaseAuth.getInstance()
    }

    fun alreadyLogedIn(checkLogedIn: (Boolean) -> Unit){
        if(auth.currentUser != null)
            checkLogedIn.invoke(true)
        else
            checkLogedIn.invoke(false)
    }
    fun signUpUser(email: String, password: String, checkSignUp: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    checkSignUp.invoke(true)
                    Log.d("MyFirebase", "signUpUser: successfully")
                } else {
                    checkSignUp.invoke(false)
                    Log.d("MyFirebase","Sign up failed: ${task.exception?.message}")
                }
            }
    }

    fun loginUser(email: String, password: String, checkLogin: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    checkLogin.invoke(true)
                    val user = auth.currentUser
                    Log.d("MyFirebase", "login: login successfully")
                } else {
                    checkLogin.invoke(false)
                    Log.d("MyFirebase","Sign up failed: ${task.exception?.message}")
                }
            }
    }
    fun logoutUser(checkLogout: (Boolean) -> Unit) {
        auth.signOut()
        checkLogout.invoke(true)
        Log.d("MyFirebase", "login: out")
    }

}
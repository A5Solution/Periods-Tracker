package com.example.periodflow.utils

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Utils {

    companion object {
        private val databaseReference = FirebaseDatabase.getInstance().reference
        private val auth = FirebaseAuth.getInstance()

        fun setValueToFirebase(key: String, value: Any, result: (Boolean) -> Unit) {
            val currentUser = auth.currentUser
            currentUser?.let { user ->
                val userEmail = user.email
                userEmail?.let { email ->
                    val usersRef = databaseReference.child("users").child(email.replace(".", "_"))
                    usersRef.child(key).setValue(value)
                        .addOnSuccessListener {
                            result.invoke(true)
                            Log.d("MyFirebase", "setValueToFirebase: Success")
                        }
                        .addOnFailureListener {
                            result.invoke(false)
                            Log.d("MyFirebase", "setValueToFirebase: Failed")
                        }
                } ?: run {
                    // User email is null
                    result.invoke(false)
                    Log.d("MyFirebase", "setValueToFirebase: User email is null")
                }
            } ?: run {
                // No user signed in
                result.invoke(false)
                Log.d("MyFirebase", "setValueToFirebase: No user signed in")
            }
        }

        fun setTwoValuesToFirebase(key1: String, value1: Any, key2: String, value2: Any, result: (Boolean) -> Unit) {
            val currentUser = auth.currentUser
            currentUser?.let { user ->
                val userEmail = user.email
                userEmail?.let { email ->
                    val key = email.replace(".", "_") // Replace dots with underscores to avoid issues with Firebase keys
                    val usersRef = databaseReference.child("users").child(key)

                    val updates = HashMap<String, Any>().apply {
                        put(key1, value1)
                        put(key2, value2)
                    }

                    usersRef.updateChildren(updates)
                        .addOnSuccessListener {
                            result.invoke(true)
                            Log.d("MyFirebase", "setTwoValuesToFirebase: Success")
                        }
                        .addOnFailureListener {
                            result.invoke(false)
                            Log.d("MyFirebase", "setTwoValuesToFirebase: Failed")
                        }
                } ?: run {
                    // User email is null
                    result.invoke(false)
                    Log.d("MyFirebase", "setTwoValuesToFirebase: User email is null")
                }
            } ?: run {
                // No user signed in
                result.invoke(false)
                Log.d("MyFirebase", "setTwoValuesToFirebase: No user signed in")
            }
        }

    }
}

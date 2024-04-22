package com.example.periodflow.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class Utils {

    companion object {
        private val databaseReference = FirebaseDatabase.getInstance().reference
        private val auth = FirebaseAuth.getInstance()

        var periodduration = ""
        var lastperioddate = ""
        var username = ""
        var periodcycle = ""
        var nextPeriod = ""

        fun setBasicInfo() {
            val currentUser = auth.currentUser
            currentUser?.let { user ->
                val userEmail = user.email
                userEmail?.let { email ->
                    val usersRef = databaseReference.child("users").child(email.replace(".", "_"))

                    val basicInfoUpdates = HashMap<String, Any>()
                    basicInfoUpdates["periodduration"] = periodduration
                    basicInfoUpdates["lastperioddate"] = lastperioddate
                    basicInfoUpdates["username"] = username
                    basicInfoUpdates["periodcycle"] = periodcycle

                    usersRef.updateChildren(basicInfoUpdates)
                        .addOnSuccessListener {
                            Log.d("MyFirebase", "setBasicInfo: Success")
                        }
                        .addOnFailureListener { e ->
                            Log.w(
                                "MyFirebase",
                                "setBasicInfo: Error writing basic info to Firebase",
                                e
                            )
                        }
                }
            }
        }

        fun fetchBasicInfo(context: Context, isRegister: (Boolean) -> Unit) {
            val currentUser = auth.currentUser
            currentUser?.let { user ->
                val userEmail = user.email
                userEmail?.let { email ->
                    val usersRef = databaseReference.child("users").child(email.replace(".", "_"))

                    // Check for internet connectivity
                    if (isNetworkAvailable(context)) {
                        usersRef.get().addOnSuccessListener { dataSnapshot ->
                            if (dataSnapshot.exists()) {
                                val basicInfo = dataSnapshot.value as Map<*, *>
                                periodduration = basicInfo["periodduration"] as? String ?: ""
                                lastperioddate = basicInfo["lastperioddate"] as? String ?: ""
                                username = basicInfo["username"] as? String ?: ""
                                periodcycle = basicInfo["periodcycle"] as? String ?: ""
                                isRegister.invoke(true)
                            } else {
                                isRegister.invoke(false)
                                Log.d("MyFirebase", "fetchBasicInfo: No data found for user.")
                            }
                        }.addOnFailureListener { e ->
                            Log.w("MyFirebase", "fetchBasicInfo: Error fetching basic info", e)
                            // Handle data fetch error (e.g., show error message to user)
                        }
                    } else {
                        Log.d("MyFirebase", "fetchBasicInfo: No internet connection available.")
                        // Handle no internet scenario (e.g., show error message to user)
                    }
                }
            }
        }

        // Helper function to check internet connectivity (replace with your preferred method)
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        fun calculateNextPeriod(): String? {
            val formatter = SimpleDateFormat("dd/MM/yyyy") // Date format for parsing
            try {
                val lastPeriodDate = formatter.parse(lastperioddate)
                val calendar = Calendar.getInstance()
                calendar.time = lastPeriodDate

                calendar.add(Calendar.DAY_OF_MONTH, periodduration.toInt())

                return formatter.format(calendar.time)
            } catch (e: Exception) {
                Log.w("MyFirebase", "calculateNextPeriod: Error parsing date or adding duration", e)
                return null
            }
        }
        fun calculateRemainingDays(): Int {
            val nextPeriodDate = calculateNextPeriod()
            if (nextPeriodDate == null) {
                return -1 // Indicate error or unknown remaining days
            }

            val today = Calendar.getInstance().timeInMillis

            val nextPeriodTime =  SimpleDateFormat("dd/MM/yyyy").parse(nextPeriodDate)?.time ?: return -1

            val difference = nextPeriodTime - today

            return (difference / (1000 * 60 * 60 * 24)).toInt().coerceAtLeast(0) // Non-negative remaining days
        }
        fun calculateFertileWindowStart(): String? {
            val formatter = SimpleDateFormat("dd/MM/yyyy") // Date format for parsing
            try {
                val lastPeriodDate = formatter.parse(lastperioddate)
                val calendar = Calendar.getInstance()
                calendar.time = lastPeriodDate

                // Add 11 days to the calendar to get the start of the fertile window
                calendar.add(Calendar.DAY_OF_MONTH, 11)

                return formatter.format(calendar.time)
            } catch (e: Exception) {
                Log.w("MyFirebase", "calculateFertileWindowStart: Error parsing date or adding duration", e)
                return null
            }
        }
        fun calculateOvulationDate(): String? {
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            try {
                val lastPeriodDate = formatter.parse(lastperioddate)
                val calendar = Calendar.getInstance()
                calendar.time = lastPeriodDate
                calendar.add(Calendar.DAY_OF_MONTH, 9)
                return formatter.format(calendar.time)
            } catch (e: Exception) {
                Log.w("MyFirebase", "calculateFertileWindowStart: Error parsing date or adding duration", e)
                return null
            }
        }

        fun setValueToFirebase(key: String, value: Any, result: (Boolean) -> Unit) {
            val currentUser = auth.currentUser
            currentUser?.let { user ->
                val userEmail = user.email
                userEmail?.let { email ->
                    val usersRef =
                        databaseReference.child("users").child(email.replace(".", "_"))
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

        fun setTwoValuesToFirebase(
            key1: String,
            value1: Any,
            key2: String,
            value2: Any,
            result: (Boolean) -> Unit
        ) {
            val currentUser = auth.currentUser
            currentUser?.let { user ->
                val userEmail = user.email
                userEmail?.let { email ->
                    val key = email.replace(
                        ".",
                        "_"
                    ) // Replace dots with underscores to avoid issues with Firebase keys
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

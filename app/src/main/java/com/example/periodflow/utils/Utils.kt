package com.example.periodflow.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.util.Log
import com.example.periodflow.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Utils {

    companion object {
        private val databaseReference = FirebaseDatabase.getInstance().reference
        private val auth = FirebaseAuth.getInstance()

        var periodduration = ""
        var lastperioddate = ""
        var username = ""
        var periodcycle = ""

        var weight = "0"
        var weightaddeddate = "00/00/0000"
        var temperature = "0"
        var temperatureaddeddate = "00/00/0000"
        var birthdaydate = "00/00/0000"

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
                    basicInfoUpdates["weight"] = weight
                    basicInfoUpdates["weightaddeddate"] = weightaddeddate
                    basicInfoUpdates["temperature"] = temperature
                    basicInfoUpdates["temperatureaddeddate"] = temperatureaddeddate
                    basicInfoUpdates["birthdaydate"] = birthdaydate

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
                                weight = basicInfo["weight"] as? String ?: ""
                                weightaddeddate = basicInfo["weightaddeddate"] as? String ?: ""
                                temperature = basicInfo["temperature"] as? String ?: ""
                                temperatureaddeddate =
                                    basicInfo["temperatureaddeddate"] as? String ?: ""
                                birthdaydate = basicInfo["birthdaydate"] as? String ?: ""
                                isRegister.invoke(true)
                            } else {
                                isRegister.invoke(false)
                                Log.d("MyFirebase", "fetchBasicInfo: No data found for user.")
                            }
                        }.addOnFailureListener { e ->
                            Log.w("MyFirebase", "fetchBasicInfo: Error fetching basic info", e)
                        }
                    } else {
                        Log.d("MyFirebase", "fetchBasicInfo: No internet connection available.")
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

            val nextPeriodTime =
                SimpleDateFormat("dd/MM/yyyy").parse(nextPeriodDate)?.time ?: return -1

            val difference = nextPeriodTime - today

            return (difference / (1000 * 60 * 60 * 24)).toInt()
                .coerceAtLeast(0) // Non-negative remaining days
        }

        fun calculateDaysSinceLastPeriod(): Int {
            val formatter = SimpleDateFormat("dd/MM/yyyy") // Date format for parsing
            try {
                val lastPeriod = formatter.parse(lastperioddate)
                val today = Calendar.getInstance().time // Current date

                // Calculate the difference in milliseconds between the two dates
                val differenceInMillis = today.time - lastPeriod.time

                // Convert milliseconds to days
                val differenceInDays = (differenceInMillis / (1000 * 60 * 60 * 24)).toInt()

                return differenceInDays
            } catch (e: Exception) {
                Log.w("MyFirebase", "calculateDaysSinceLastPeriod: Error parsing date", e)
                return -1 // Return -1 if there's an error
            }
        }

        fun calculateFertileWindowStart(): String? {
            val formatter = SimpleDateFormat("dd/MM/yyyy") // Date format for parsing
            try {
                val lastPeriodDate = formatter.parse(lastperioddate)
                val calendar = Calendar.getInstance()
                calendar.time = lastPeriodDate

                calendar.add(Calendar.DAY_OF_MONTH, 11)

                return formatter.format(calendar.time)
            } catch (e: Exception) {
                Log.w(
                    "MyFirebase",
                    "calculateFertileWindowStart: Error parsing date or adding duration",
                    e
                )
                return null
            }
        }

        fun calculateOvulationDate(): String? {
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            try {
                val lastPeriodDate = formatter.parse(lastperioddate)
                val calendar = Calendar.getInstance()
                calendar.time = lastPeriodDate
                calendar.add(Calendar.DAY_OF_MONTH, 13)
                return formatter.format(calendar.time)
            } catch (e: Exception) {
                Log.w(
                    "MyFirebase",
                    "calculateFertileWindowStart: Error parsing date or adding duration",
                    e
                )
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

        fun contactUs(context: Context) {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:shahzaibm968@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email")
            context.startActivity(intent)
        }

        fun openPlayStoreForRating(context: Context) {
            val appPackageName = context.packageName
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (e: android.content.ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }

        fun openPrivacyPolicy(context: Context, link: String) {
            val privacyPolicyUrl = link // Replace with your actual URL

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(privacyPolicyUrl)
            context.startActivity(intent)
        }

        fun shareApp(context: Context) {
            val appName = context.getString(R.string.app_name)

            val appStoreUrl =
                "https://play.google.com/store/apps/details?id=sparx.periodtracker.mycalendar.ovulationtracker.menstrual.flo.cycletracker"

            val message = if (appStoreUrl.isNotBlank()) {
                "Hey! Check out this awesome app, $appName:\n$appStoreUrl"
            } else {
                "Hey! Check out this cool app, $appName. Install it now!"
            }

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)

            val chooserIntent = Intent.createChooser(shareIntent, "Share $appName")
            context.startActivity(chooserIntent)
        }
        fun formatDate(): String {
            val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
            val date = inputFormat.parse(lastperioddate)
            return outputFormat.format(date)
        }
        fun formatDate(date: String): String {
            val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
            val date = inputFormat.parse(date)
            return outputFormat.format(date)
        }

        fun calculateNextDate(progress: Int): String {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val lastPeriodDate = formatter.parse(lastperioddate)

            val calendar = Calendar.getInstance()
            calendar.time = lastPeriodDate

            calendar.add(Calendar.DAY_OF_MONTH, progress)

            return formatter.format(calendar.time)
        }
        fun calculatePeriodDates(): Pair<String, String> {
            // Parse the last period date string into a Date object
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val lastPeriod = formatter.parse(lastperioddate)

            // Create a Calendar instance and set it to the last period date
            val calendar = Calendar.getInstance()
            calendar.time = lastPeriod

            // Calculate the start date of the period cycle
            val startDate = calendar.clone() as Calendar // Create a clone to avoid modifying the original calendar
            startDate.add(Calendar.DAY_OF_MONTH, periodcycle.toInt())

            // Calculate the end date of the period cycle
            val endDate = calendar.clone() as Calendar // Create a clone to avoid modifying the original calendar
            endDate.add(Calendar.DAY_OF_MONTH, -0) // End date is one day before the last period date

            // Format the calculated dates into the desired format
            val startDateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
            val endDateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())

            val startDateString = startDateFormat.format(startDate.time)
            val endDateString = endDateFormat.format(endDate.time)

            return Pair(startDateString, endDateString)
        }
    }
}

package ir.fastclick.core

import android.content.Context
import android.content.SharedPreferences
import android.os.Build

class TavoosSDK {

    private var preRollPrefixAddress = "https://video.tavoos.net/services/mobile/vast/"
    private var secretKey: String = ""

    companion object {
        private const val SHARED_PREFERENCES_KEY = "AdvertiseIdPrefs"
        private const val ADVERTISE_ID_KEY = "advertiseId"
    }

    fun setSecretKey(key: String) {
        secretKey = key
    }

    fun getVastAddress(context: Context): String {
        val advertisingId = getOrCreateAdvertisingId(context)
        val packageName = context.packageName

        val params = mapOf(
            "device-model" to Build.MODEL,
            "deviceBrand" to Build.BRAND,
            "device-os" to "android",
            "osVersion" to Build.VERSION.SDK_INT.toString(),
            "osAdvertisingId" to advertisingId,
            "app-package-name" to packageName
        )

        val paramString = params.map { (key, value) -> "$key=$value" }.joinToString("&")
        return "$preRollPrefixAddress$secretKey?$paramString"
    }

    private fun getOrCreateAdvertisingId(context: Context): String {
        var id = getAdvertiseIdFromSharedPreferences(context)
        if (id.isEmpty()) {
            id = generateRandomHexString()
            saveAdvertiseIdToSharedPreferences(context, id)
        }
        return id
    }

    private fun saveAdvertiseIdToSharedPreferences(context: Context, advertiseId: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(ADVERTISE_ID_KEY, advertiseId)
        editor.apply()
    }

    private fun getAdvertiseIdFromSharedPreferences(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(ADVERTISE_ID_KEY, "") ?: ""
    }

    private fun generateRandomHexString(length: Int = 13): String {
        val allowedChars = "0123456789abcdef"
        return (1..length).map { allowedChars.random() }.joinToString("")
    }
}

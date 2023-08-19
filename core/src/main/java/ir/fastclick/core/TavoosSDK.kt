package ir.fastclick.core

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import java.io.IOException
import java.util.UUID

class TavoosSDK {

    private var preRollPrefixAddress = "https://video.tavoos.net/services/mobile/vast/"
    private var secretKey: String = "1234" // Default secret key

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
            "app-package-name" to packageName,
            "secretKey" to secretKey
        )

        val paramString = params.map { (key, value) -> "$key=$value" }.joinToString("&")
        return "$preRollPrefixAddress?$paramString"
    }

    private fun getAdvertisingId(context: Context): String {
        return try {
            val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
            val advertisingId = adInfo.id ?: ""
            advertisingId
        } catch (e: IOException) {
            ""
        } catch (e: GooglePlayServicesNotAvailableException) {
            ""
        } catch (e: GooglePlayServicesRepairableException) {
            ""
        }
    }

    private fun generateRandomAdvertiseId(): String {
        return UUID.randomUUID().toString()
    }

    private fun saveAdvertiseIdToSharedPreferences(context: Context, advertiseId: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("AdvertiseIdPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("AdvertiseId", advertiseId)
        editor.apply()
    }

    private fun getAdvertiseIdFromSharedPreferences(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("AdvertiseIdPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("AdvertiseId", "") ?: ""
    }


    private fun getOrCreateAdvertisingId(context: Context): String {
        var advertisingId = getAdvertisingId(context)
        if (advertisingId.isEmpty()) {
            advertisingId = getAdvertiseIdFromSharedPreferences(context)
            if (advertisingId.isEmpty()) {
                advertisingId = generateRandomAdvertiseId()
                saveAdvertiseIdToSharedPreferences(context, advertisingId)
            }
        }
        return advertisingId
    }

}

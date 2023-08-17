package ir.fastclick.core

import android.content.Context
import android.os.Build
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import java.io.IOException

class TavoosSDK {

    private var preRollPrefixAddress = "http://192.168.100.100:7000/services/mobile/vast/"
    private var secretKey: String = "1234" // Default secret key

    fun setSecretKey(key: String) {
        secretKey = key
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

    fun getVastAddress(context: Context): String {
        val advertisingId = getAdvertisingId(context)
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
}

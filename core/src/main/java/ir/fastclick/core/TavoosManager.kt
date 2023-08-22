package ir.fastclick.core

import android.content.Context

class TavoosManager {

    private val tavoosSDK = TavoosSDK()

    fun setSecretKey(key: String) {
        tavoosSDK.setSecretKey(key)
    }

    fun getVastAddress(context: Context): String {
        return tavoosSDK.getVastAddress(context)
    }
}

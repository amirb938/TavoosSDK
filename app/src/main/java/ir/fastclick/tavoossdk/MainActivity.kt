package ir.fastclick.tavoossdk

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ir.fastclick.core.TavoosManager

class MainActivity : AppCompatActivity() {
    private lateinit var textHome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textHome = findViewById(R.id.textHome)

        val tavoosSDK = TavoosManager().apply {
            setSecretKey("example-key")
        }
        val text = tavoosSDK.getVastAddress(this@MainActivity)
        textHome.text = text
    }
}
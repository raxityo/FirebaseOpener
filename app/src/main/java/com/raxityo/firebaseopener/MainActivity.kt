package com.raxityo.firebaseopener

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (handleIntent(intent)) return
        setContentView(R.layout.activity_main)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent): Boolean {
        if (intent.action != Intent.ACTION_VIEW) return false
        val appLinkData = intent.dataString ?: return false

        val destinationLink = "${ACCOUNT_SELECTOR_PREFIX}${appLinkData}".toUri()
        startActivity(Intent(Intent.ACTION_VIEW, destinationLink))
        finish()
        return true
    }

    companion object {
        private const val ACCOUNT_SELECTOR_PREFIX = "https://accounts.google.com/SignOutOptions?continue="
    }
}
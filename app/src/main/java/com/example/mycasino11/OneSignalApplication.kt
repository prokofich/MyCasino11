package com.example.mycasino11

import android.app.Application
import com.onesignal.OneSignal

class OneSignalApplication: Application() {

    val ONESIGNAL_APP_ID = "19b627d4-9b62-4307-a630-996e6c917ecb"

    override fun onCreate() {
        super.onCreate()

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}
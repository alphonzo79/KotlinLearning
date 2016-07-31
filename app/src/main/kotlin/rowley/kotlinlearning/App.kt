package rowley.kotlinlearning

import android.app.Application
import rowley.kotlinlearning.extensions.DelegatesExt

class App : Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValueVar()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
package grabteacher.com.mobileui;

import android.app.Application
import timber.log.Timber

/**
 * Created by Huu Hoang on 30/11/2018
 */
class GitApp: Application() {
    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }

    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
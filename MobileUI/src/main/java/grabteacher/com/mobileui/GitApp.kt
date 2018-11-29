package grabteacher.com.mobileui;

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import grabteacher.com.mobileui.di.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Huu Hoang on 30/11/2018
 */
class GitApp : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: AndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return  androidInjector
    }
}
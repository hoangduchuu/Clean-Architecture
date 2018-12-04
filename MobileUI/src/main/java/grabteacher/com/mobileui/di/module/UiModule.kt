package grabteacher.com.mobileui.di.module;

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import grabteacher.com.domain.executor.PostExecutionThread
import grabteacher.com.mobileui.BrowseActivity.BrowseActivity
import grabteacher.com.mobileui.thread.UIThread

/**
 * Created by Huu Hoang on 02/12/2018
 */
@Module
abstract class UiModule {
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread


    @ContributesAndroidInjector
    abstract fun bindontributesAndroidInjectorForBrowseactivity(): BrowseActivity
}
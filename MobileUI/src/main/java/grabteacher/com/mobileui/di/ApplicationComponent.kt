package grabteacher.com.mobileui.di;

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import grabteacher.com.mobileui.GitApp
import grabteacher.com.mobileui.di.module.*
import javax.inject.Singleton

/**
 * Created by Huu Hoang on 30/11/2018
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
        ApplicationModule::class,
        UiModule::class,PresentationModule::class, DataModule::class, RemoteModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface  Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build() : ApplicationComponent
    }

    fun inject(app: GitApp)
}
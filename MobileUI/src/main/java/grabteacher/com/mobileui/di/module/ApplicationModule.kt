package grabteacher.com.mobileui.di.module;

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import grabteacher.com.mobileui.GitApp

/**
 * Created by Huu Hoang on 02/12/2018
 */
@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindContext(app: Application) : Context


}
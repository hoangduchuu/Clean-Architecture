package grabteacher.com.mobileui.di.module;

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import grabteacher.com.cache.db.ProjectsDatabase
import grabteacher.com.cache.store.ProjectsCacheImpl
import grabteacher.com.data.repositories.cache.ProjectsCache


/**
 * Created by Huu Hoang on 02/12/2018
 */
@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}
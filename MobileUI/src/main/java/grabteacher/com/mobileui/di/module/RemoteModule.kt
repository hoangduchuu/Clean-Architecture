package grabteacher.com.mobileui.di.module;

import dagger.Binds
import dagger.Module
import dagger.Provides
import grabteacher.com.data.repositories.remote.ProjectsRemote
import grabteacher.com.mobileui.GitApp
import grabteacher.com.remote.ProjectRemoteImp
import grabteacher.com.remote.services.GithubServices
import grabteacher.com.remote.services.GithubTrendingServiceFactory

/**
 * Created by Huu Hoang on 02/12/2018
 */
@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubServices {
            return GithubTrendingServiceFactory.makeGithubTrendingService(false)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectRemoteImp): ProjectsRemote
}
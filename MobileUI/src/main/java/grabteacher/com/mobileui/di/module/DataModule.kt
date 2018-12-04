package grabteacher.com.mobileui.di.module;

import dagger.Binds
import dagger.Module
import grabteacher.com.data.repositories.ProjectsDataRepository
import grabteacher.com.domain.repository.ProjectRepository

/**
 * Created by Huu Hoang on 02/12/2018
 */
@Module
abstract class DataModule {

    @Binds
    abstract fun  bindDataRepository(proectRepository: ProjectsDataRepository) : ProjectRepository
}
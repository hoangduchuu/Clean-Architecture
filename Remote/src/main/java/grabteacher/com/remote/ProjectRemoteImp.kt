package grabteacher.com.remote

import grabteacher.com.data.model.ProjectEntity
import grabteacher.com.data.repositories.remote.ProjectsRemote
import grabteacher.com.remote.mapper.ProjectsResponseModelMapper
import grabteacher.com.remote.services.GithubServices
import io.reactivex.Observable
import javax.inject.Inject

class ProjectRemoteImp @Inject constructor(
        private val services: GithubServices,
        private val mapper:ProjectsResponseModelMapper
): ProjectsRemote{



    override fun getProjects(): Observable<List<ProjectEntity>> {
        return services.searchRepositories("language:kotlin","starts","desc")
                .map {
                    it.items.map {
                        mapper.mapFromModel(it)
                    }
                }
    }

}
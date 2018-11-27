package grabteacher.com.data.repositories

import grabteacher.com.data.mapper.ProjectMapper
import grabteacher.com.data.model.ProjectEntity
import grabteacher.com.data.repositories.cache.ProjectsCache
import grabteacher.com.domain.model.Project
import grabteacher.com.domain.repository.ProjectRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val factory: ProjectDataStoreFactory,
        private val cache: ProjectsCache) : ProjectRepository {
    
    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(), cache.isProjectsCacheExpired().toObservable(),

                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areProjectsCached, isProjectsCacheExpired ->
                    Pair(areProjectsCached, isProjectsCacheExpired)
                })
                .flatMap { pair ->
                    val areProjectsCached = pair.first
                    val isProjectsCacheExpired = pair.second
                    factory.getDataStore(areProjectsCached, isProjectsCacheExpired).getProjects()
                }.flatMap { projects ->
                    factory.getCacheDataStore()
                            .saveProjects(projects)
                            .andThen(Observable.just(projects))
                }.map {
                    it.map { mapper.mapFromEntity(it) }
                }
    }

    override fun bookMarkProject(projectID: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectID)
    }

    override fun unBookMarkProject(projectID: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectID)
    }

    override fun getBookMarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

}
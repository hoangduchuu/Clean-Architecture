package grabteacher.com.data.repositories

import grabteacher.com.data.model.ProjectEntity
import grabteacher.com.data.repositories.cache.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectCacheDataStoreImp @Inject constructor(var projectsCache: ProjectsCache) : ProjectDataStore {
    override fun clearProjects(): Completable {
      return  projectsCache.clearCache()
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }


    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
                .andThen { projectsCache.setLastedCacheExpired(System.currentTimeMillis()) }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

}
package grabteacher.com.data.repositories.cache

import grabteacher.com.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ProjectsCache {
    fun clearCache(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectID: String) : Completable

    fun setProjectAsUnBookmarked(projectID: String) : Completable

    fun areProjectsCached(): Single<Boolean>

    fun setLastedCacheExpired(currentTimeMillis: Long): Single<Boolean>

    fun isProjectsCacheExpired(): Single<Boolean>

}
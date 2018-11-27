package grabteacher.com.cache.store

import android.provider.Settings
import grabteacher.com.cache.db.ProjectsDatabase
import grabteacher.com.cache.mapper.CachedProjectMapper
import grabteacher.com.cache.models.Config
import grabteacher.com.data.model.ProjectEntity
import grabteacher.com.data.repositories.cache.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor() : ProjectsCache {
    @Inject
    lateinit var projectsDatabase: ProjectsDatabase

    @Inject
    lateinit var mapper: CachedProjectMapper

    override fun clearCache(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao()
                    .insertProjects(projects.map {
                        mapper.mapToCached(it)
                    })
            Completable.complete()
        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
                .toObservable()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
                .toObservable()
                .map {
                    it.map {
                        mapper.mapFromCached(it)
                    }
                }
    }

    override fun setProjectAsBookmarked(projectID: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true, projectID)
            Completable.complete()
        }
    }

    override fun setProjectAsUnBookmarked(projectID: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(false, projectID)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectsDatabase.cachedProjectsDao().getProjects().isEmpty
                .map {
                    !it
                }
    }

    override fun setLastedCacheExpired(currentTimeMillis: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = currentTimeMillis))
            Completable.complete()
        }

    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis();
        val expirationTime = (60 * 10 * 1000).toLong()

        return projectsDatabase.configDao().getConfig()
                .single(Config(lastCacheTime = 0))
                .map { currentTime - it.lastCacheTime > expirationTime }
    }

}
package grabteacher.com.data.repositories

import grabteacher.com.data.model.ProjectEntity
import grabteacher.com.data.repositories.remote.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class ProjectRemoteDataStoreImp @Inject constructor(var projectRemote: ProjectsRemote) : ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
      return projectRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("saveProjects projects isn't supported here...")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("clearProjects projects isn't supported here...")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("getBookmarkedProjects projects isn't supported here...")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("setProjectAsBookmarked projects isn't supported here...")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("setProjectAsNotBookmarked projects isn't supported here...")
    }


}
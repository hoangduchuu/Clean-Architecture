package grabteacher.com.domain.repository

import grabteacher.com.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectRepository {
    fun getProjects() : Observable<List<Project>>

    fun bookMarkProject(projectID: String): Completable

    fun unBookMarkProject(projectID: String): Completable

    fun getBookMarkedProjects() : Observable<List<Project>>
}
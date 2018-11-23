package grabteacher.com.data.repositories.remote

import grabteacher.com.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote{

    fun getProjects(): Observable<List<ProjectEntity>>



}
package grabteacher.com.domain.usecase

import grabteacher.com.domain.executor.PostExecutionThread
import grabteacher.com.domain.interactor.ObservableUseCase
import grabteacher.com.domain.model.Project
import grabteacher.com.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetProjectUseCase @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val postExecutionThread: PostExecutionThread):ObservableUseCase<List<Project>, Nothing?>(postExecutionThread){

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getProjects()
    }

}
package grabteacher.com.domain.usecase

import grabteacher.com.domain.executor.PostExecutionThread
import grabteacher.com.domain.interactor.CompletableUseCase
import grabteacher.com.domain.repository.ProjectRepository
import io.reactivex.Completable
import sun.awt.SunToolkit
import java.lang.IllegalArgumentException
import javax.inject.Inject

open class BookParkProjectUseCase @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val postExecutionThread: PostExecutionThread) : CompletableUseCase<BookParkProjectUseCase.Params>(postExecutionThread) {

    public  override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can not be null")
        return projectRepository.bookMarkProject(params.projectID)
    }

    data class Params constructor(val projectID: String){
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }

    }

}


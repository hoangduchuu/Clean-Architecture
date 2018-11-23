package grabteacher.com.domain.projects

import com.nhaarman.mockito_kotlin.whenever
import grabteacher.com.domain.executor.PostExecutionThread
import grabteacher.com.domain.model.Project
import grabteacher.com.domain.repository.ProjectRepository
import grabteacher.com.domain.test.ProjectDataFactory
import grabteacher.com.domain.usecase.GetBookmarkedProjectsUseCase
import grabteacher.com.domain.usecase.GetProjectUseCase
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedProjectsUseCaseTest {

   private lateinit var getProjectsUseCase: GetBookmarkedProjectsUseCase

    @Mock
    lateinit var projectRepository: ProjectRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProjectsUseCase = GetBookmarkedProjectsUseCase(projectRepository, postExecutionThread)

    }

    @Test
    fun getProjectCompletes() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectLists(3)))
        val testObservable =  getProjectsUseCase.buildUseCaseObservable().test()
        testObservable.assertComplete()
    }

    @Test
    fun getProjectReturnData() {

        val projects = ProjectDataFactory.makeProjectLists(3)
        stubGetProjects(Observable.just(projects))
        val testObservable =  getProjectsUseCase.buildUseCaseObservable().test()

        testObservable.assertValue(projects)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectRepository.getBookMarkedProjects())
                .thenReturn(observable)
    }

}
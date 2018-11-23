package grabteacher.com.domain.projects

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import grabteacher.com.domain.executor.PostExecutionThread
import grabteacher.com.domain.model.Project
import grabteacher.com.domain.repository.ProjectRepository
import grabteacher.com.domain.test.ProjectDataFactory
import grabteacher.com.domain.usecase.BookParkProjectUseCase
import grabteacher.com.domain.usecase.GetProjectUseCase
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException

class BookmarkProjectUseCaseTest {

    private lateinit var bookMarkProjectUseCase: BookParkProjectUseCase

    @Mock
    lateinit var projectRepository: ProjectRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bookMarkProjectUseCase = BookParkProjectUseCase(projectRepository, postExecutionThread)

    }

    @Test
    fun getProjectCompletes() {
        stubBookMarkProject(Completable.complete())

        val params = BookParkProjectUseCase.Params.forProject(ProjectDataFactory.randomUUID())

        val testcompleteAble = bookMarkProjectUseCase.buildUseCaseCompletable(params).test()

        testcompleteAble.assertComplete()

    }

    @Test(expected = IllegalArgumentException::class)
    fun getProjectThrowExeption() {
        bookMarkProjectUseCase.buildUseCaseCompletable().test()
    }

    private fun stubBookMarkProject(completable: Completable) {
        whenever(projectRepository.bookMarkProject(any()))
                .thenReturn(completable)
    }

}
package rabteacher.com.remote.mapper

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import grabteacher.com.data.model.ProjectEntity
import grabteacher.com.remote.ProjectRemoteImp
import grabteacher.com.remote.mapper.ProjectsResponseModelMapper
import grabteacher.com.remote.model.ProjectModel
import grabteacher.com.remote.model.ProjectsResponseModel
import grabteacher.com.remote.services.GithubServices
import grabteacher.com.remote.test.factory.ProjectsDataFactory
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ProjectsRemoteImpTest {
    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubServices>()
    private val remote = ProjectRemoteImp(service, mapper)


    @Test
    fun projectsCompletes() {
        stubProjectsRepositories(Observable.just(ProjectsDataFactory.makeProjectsResponse())) // clone

        stubGetProjectAndReturnData(any(), ProjectsDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test() // trigger observable run
        testObserver.assertComplete() // verify
    }


    @Test
    fun getProjectsCallServices() {
        stubProjectsRepositories(Observable.just(ProjectsDataFactory.makeProjectsResponse())) // clone

        stubGetProjectAndReturnData(any(), ProjectsDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test() // trigger call
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getPrjectsAndReturnData() {
        val response = ProjectsDataFactory.makeProjectsResponse()
        stubProjectsRepositories(Observable.just(response))

        val entities =  mutableListOf<ProjectEntity>()
        response.items.forEach { it ->
            val entity = ProjectsDataFactory.makeProjectEntity()
            entities.add(entity)

            stubGetProjectAndReturnData(it,entity)
        }

        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)  // verify the same data

    }


    private fun stubGetProjectAndReturnData(model: ProjectModel, entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
                .thenReturn(entity)
    }

    /**
     * clone response
     */
    private fun stubProjectsRepositories(observable: Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
                .thenReturn(observable)

    }
}
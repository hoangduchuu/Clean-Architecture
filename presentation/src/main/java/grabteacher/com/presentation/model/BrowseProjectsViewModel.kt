package grabteacher.com.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import grabteacher.com.domain.model.Project
import grabteacher.com.domain.usecase.BookParkProjectUseCase
import grabteacher.com.domain.usecase.GetProjectUseCase
import grabteacher.com.domain.usecase.UnBookParkProjectUseCase
import grabteacher.com.presentation.mapper.ProjectViewMapper
import grabteacher.com.presentation.state.Resource
import grabteacher.com.presentation.state.ResourseState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

open class BrowseProjectsViewModel @Inject internal constructor(
        private val getProjects: GetProjectUseCase?,
        private val bookmarkProject: BookParkProjectUseCase,
        private val unBookmarkProject: UnBookParkProjectUseCase,
        private val mapper: ProjectViewMapper): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    init {
        fetchProjects()
    }

    override fun onCleared() {
        getProjects?.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourseState.LOADING, null, null))
        getProjects?.execute(ProjectsSubscriber())
    }

    fun bookmarkProject(projectId: String) {
        return bookmarkProject.execute(BookmarkProjectsSubscriber(),
                BookParkProjectUseCase.Params.forProject(projectId))
    }

    fun unbookmarkProject(projectId: String) {
        return unBookmarkProject.execute(BookmarkProjectsSubscriber(),
                UnBookParkProjectUseCase.Params.forProject(projectId))
    }

    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourseState.SUCCESS,
                    t.map { mapper.maptoView(it) }, null))
        }

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourseState.ERROR, null, e.localizedMessage))
        }

    }

    inner class BookmarkProjectsSubscriber: DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourseState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourseState.ERROR, liveData.value?.data,
                    e.localizedMessage))
        }

    }
}
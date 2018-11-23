package grabteacher.com.data.repositories

import javax.inject.Inject

open class ProjectDataStoreFactory @Inject constructor(
        private val projectCacheData: ProjectCacheDataStoreImp,
        private val projectRemoteData: ProjectRemoteDataStoreImp) {

    open  fun getDataStore(projectcached: Boolean, projectExpired: Boolean): ProjectDataStore {
        return if (projectcached && !projectExpired) {
            projectCacheData
        } else projectRemoteData
    }

    open fun getCacheDataStore(): ProjectDataStore {
        return projectCacheData
    }

}
package grabteacher.com.data.store

import com.nhaarman.mockito_kotlin.mock
import grabteacher.com.data.repositories.ProjectCacheDataStoreImp
import grabteacher.com.data.repositories.ProjectDataStoreFactory
import grabteacher.com.data.repositories.ProjectRemoteDataStoreImp
import org.junit.Test
import kotlin.test.assertEquals


class ProjectsDataStoreTest{
    private val cacheStore = mock<ProjectCacheDataStoreImp>()
    private val remoteStore = mock<ProjectRemoteDataStoreImp>()
    private val factory = ProjectDataStoreFactory(cacheStore,remoteStore)


    @Test
    fun getDataStoreReturnRemoteStoreWhenCacheExpired(){
        assertEquals(remoteStore,factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnRemoteStoreWhenProjectNotCached(){
        assertEquals(remoteStore,factory.getDataStore(false, false))
    }


    @Test
    fun getDataStoreReturnCacheStoreWhenCacheisUnExpired(){
        assertEquals(cacheStore,factory.getDataStore(true, false))
    }



    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}
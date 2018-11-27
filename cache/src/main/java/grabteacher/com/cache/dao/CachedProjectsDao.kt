package grabteacher.com.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import grabteacher.com.cache.db.ProjectConstants
import grabteacher.com.cache.db.ProjectConstants.DELETE_PROJECTS
import grabteacher.com.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import grabteacher.com.cache.db.ProjectConstants.QUERY_PROJECTS
import grabteacher.com.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import grabteacher.com.cache.models.CachedProject
import io.reactivex.Flowable

@Dao
abstract class CachedProjectsDao{
    @Query(QUERY_PROJECTS)
    @JvmSuppressWildcards
    abstract fun getProjects(): Flowable<List<CachedProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertProjects(projects: List<CachedProject>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean,
                                   projectId: String)

}
package grabteacher.com.cache.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import grabteacher.com.cache.db.ProjectConstants

/**
 * match Project Entity
 */
@Entity(tableName = ProjectConstants.TABLE_NAME)
data class CachedProject(
        @PrimaryKey
        @ColumnInfo(name = ProjectConstants.COLUMN_PROJECT_ID)
        var id: String,
        var name: String,
        var fullName: String,
        var starCount: String,
        var dateCreated: String,
        var ownerName: String,
        var ownerAvatar: String,
        @ColumnInfo(name = ProjectConstants.COLUMN_IS_BOOKMARKED)
        var isBookmarked: Boolean
)
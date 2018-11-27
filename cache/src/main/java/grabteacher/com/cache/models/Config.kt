package grabteacher.com.cache.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import grabteacher.com.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
        @PrimaryKey(autoGenerate = true)
        var id: Int = -1,
        var lastCacheTime: Long)
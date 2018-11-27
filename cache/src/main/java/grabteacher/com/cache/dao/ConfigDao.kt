package grabteacher.com.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import grabteacher.com.cache.db.ConfigConstants
import grabteacher.com.cache.models.Config
import io.reactivex.Flowable

@Dao
abstract class ConfigDao {

    @Query(ConfigConstants.QUERY_CONFIG)
    abstract fun getConfig(): Flowable<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)

}
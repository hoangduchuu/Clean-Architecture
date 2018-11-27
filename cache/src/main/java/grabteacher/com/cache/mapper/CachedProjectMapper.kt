package grabteacher.com.cache.mapper

import grabteacher.com.cache.models.CachedProject
import grabteacher.com.data.model.ProjectEntity
import javax.inject.Inject

class CachedProjectMapper @Inject constructor() : CacheMapper<CachedProject, ProjectEntity> {
    override fun mapFromCached(cache: CachedProject): ProjectEntity {
        return ProjectEntity(
                cache.id, cache.name, cache.fullName, cache.starCount, cache.dateCreated,
                cache.ownerName, cache.ownerAvatar, cache.isBookmarked)
    }

    override fun mapToCached(entity: ProjectEntity): CachedProject {
        return CachedProject(entity.id, entity.name, entity.fullName, entity.starCount, entity.dateCreated,
                entity.ownerName, entity.ownerAvatar, entity.isBookmarked)
    }

}
package grabteacher.com.remote.mapper

import grabteacher.com.data.model.ProjectEntity
import grabteacher.com.remote.model.ProjectModel

open class ProjectsResponseModelMapper :
        ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName,
                model.starCount.toString(), model.dateCreated, model.owner.ownerName,
                model.owner.ownerAvatar,false)
    }

}
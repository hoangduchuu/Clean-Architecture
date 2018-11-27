package grabteacher.com.presentation.mapper

import grabteacher.com.domain.model.Project
import grabteacher.com.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapper @Inject constructor():Mapper<ProjectView,Project>{
    override fun maptoView(type: Project): ProjectView {
        return ProjectView(type.id,type.name,type.fullName,type.starCount,type.dateCreated,type.ownerName,type.ownerAvatar,type.isBookmarked)
    }

}
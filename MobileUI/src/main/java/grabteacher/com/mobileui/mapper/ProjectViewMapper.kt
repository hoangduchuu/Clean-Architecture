package grabteacher.com.mobileui.mapper;

import grabteacher.com.mobileui.model.Project
import grabteacher.com.presentation.model.ProjectView
import javax.inject.Inject

/**
 * Created by Huu Hoang on 02/12/2018
 */
class ProjectViewMapper @Inject constructor(): ViewMapper<ProjectView, Project> {

    override fun mapToView(presentation: ProjectView): Project {
        return Project(presentation.id, presentation.name,
                presentation.fullName, presentation.starCount,
                presentation.dateCreated, presentation.ownerName,
                presentation.ownerAvatar, presentation.isBookmarked)
    }

}
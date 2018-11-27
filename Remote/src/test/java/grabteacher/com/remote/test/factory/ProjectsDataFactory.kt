package grabteacher.com.remote.test.factory

import grabteacher.com.data.model.ProjectEntity
import grabteacher.com.remote.model.OwnerModel
import grabteacher.com.remote.model.ProjectModel
import grabteacher.com.remote.model.ProjectsResponseModel

object ProjectsDataFactory{
    fun makeOwner(): OwnerModel {
        return OwnerModel(DataFactory.randomString(), DataFactory.randomString())
    }

    fun makeProject(): ProjectModel {
        return ProjectModel(DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomInt(),
                DataFactory.randomString(), makeOwner())
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomString(),
                DataFactory.randomString(),DataFactory.randomBoolean())
    }

    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }
}
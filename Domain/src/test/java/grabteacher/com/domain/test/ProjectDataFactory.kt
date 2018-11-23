package grabteacher.com.domain.test

import grabteacher.com.domain.model.Project
import java.util.*

object ProjectDataFactory{
    fun randomUUID():String{
        return UUID.randomUUID().toString()
    }


    fun randomBoolean():Boolean{
        return Math.random() < 0.5
    }

    fun makeProject():Project{
        return Project(randomUUID(), randomUUID(), randomUUID(), randomUUID(), randomUUID(), randomUUID(), randomUUID(), randomBoolean())
    }

    fun makeProjectLists(count : Int): List<Project>{
        val projects  = mutableListOf<Project>()

        repeat(count){
            projects.add(makeProject())
        }

        return projects
    }
}
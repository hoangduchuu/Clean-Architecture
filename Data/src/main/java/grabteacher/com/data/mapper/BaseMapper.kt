package grabteacher.com.data.mapper

interface BaseMapper<E,D>{

    fun mapFromEntity(entity :E):D

    fun mapToEntity(domain :D):E
}
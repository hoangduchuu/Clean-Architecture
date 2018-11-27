package grabteacher.com.presentation.mapper

interface Mapper<VIEWMODEL, DOMAIN>{
    fun maptoView(type: DOMAIN) : VIEWMODEL
}
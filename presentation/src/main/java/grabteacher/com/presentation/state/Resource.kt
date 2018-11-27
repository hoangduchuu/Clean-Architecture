package grabteacher.com.presentation.state

class Resource<out T> constructor(val status: ResourseState,
                                  val data: T?,
                                  val message: String?) {

    fun <T> success(data: T): Resource<T> {
        return Resource(ResourseState.SUCCESS, data, null)
    }

    fun <T> error(message: String?): Resource<T> {
        return Resource(ResourseState.ERROR, null, message)
    }

    fun <T> loading(): Resource<T> {
        return Resource(ResourseState.LOADING, null, null)
    }

}
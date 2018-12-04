package grabteacher.com.mobileui.mapper;

/**
 * Created by Huu Hoang on 02/12/2018
 */
interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V

}
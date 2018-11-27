package grabteacher.com.cache.mapper

/**
 * the base class use to map Cache layer to Data layer, and nguoc lai
 */

/**
 * @CACHE IS CURRENT MODEL LAYER
 * @ENTITY IS THE HIGHGER-MODEL LAYER
 */
interface CacheMapper<CACHE, ENTITY> {

    fun mapFromCached(type: CACHE): ENTITY

    fun mapToCached(type: ENTITY): CACHE

}
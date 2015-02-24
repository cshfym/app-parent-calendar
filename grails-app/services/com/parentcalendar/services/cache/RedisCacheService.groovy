package com.parentcalendar.services.cache

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import org.apache.commons.logging.LogFactory
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import redis.clients.jedis.Protocol

import javax.servlet.http.HttpServletRequest

@Aspect
@Component
class RedisCacheService {

  def grailsApplication

  private static final log = LogFactory.getLog(this)

  Gson gson = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();

  private JedisPool pool

  public void setCache(String key, String data, int ttl) {

    Jedis jedis = getPool()?.getResource()

    if (!jedis || !key || !data) {
      return // Caching not available.
    }

    jedis.set(key, data)
    jedis.expire(key, ttl)
    log.info("Setting cache at key $key for $ttl.")
    getPool().returnResource(jedis)
  }

  public String getCache(String key) {

    Jedis jedis = getPool()?.getResource()

    if (!jedis || !key) {
      return null // Caching not available.
    }

    String data = jedis.get(key)
    getPool().returnResource(jedis)

    if (!data) {
      log.info("No cache found in pool for key $key.")
      return null
    }

    log.info("Cache FOUND in pool for key $key.")
    data
  }

  protected JedisPool getPool() {
    if (!pool) {
      initializePool()
    }
    pool
  }

  private void initializePool() {

    if (!pool) {
      log.info("Initializing cache pool.")
      try {
        URI redisUri = new URI(getRedisUrl())
        pool = new JedisPool(new JedisPoolConfig(),
          redisUri.getHost(),
          redisUri.getPort(),
          Protocol.DEFAULT_TIMEOUT,
          getRedisAuthentication())
      } catch (URISyntaxException ex) {
        log.warn ex.getStackTrace(), ex
        log.warn "Caching will NOT be available."
        // Caching not available - log and swallow exception.
      } catch (Exception ex) {
        log.warn ex.getStackTrace(), ex
        log.warn "Caching will NOT be available."
        // Caching not available - log and swallow exception.
      }
    }
  }

  def getRedisUrl() {
    grailsApplication.config.redis.url
  }

  def getRedisAuthentication() {
    grailsApplication.config.redis.authentication
  }



  /**
   * Pointcut for all methods annotated with <code>@Cacheable</code>

   @Pointcut("execution(@Cacheable * *.*(..))")
    private void cache() { }

   @Around("cache()")
    public Object aroundCachedMethods(ProceedingJoinPoint joinPoint) throws Throwable {

    // Get request context.
    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()

    // Get request URI append query parameters if necessary.
    String requestUri = req.getRequestURI()
    if(null != req.getQueryString() && !req.getQueryString().isEmpty()) {
    requestUri += "?" + req.getQueryString()
    }

    Object result = null

    Object cacheResult = getCache(requestUri.toString(), Object.class)

    // Cache not found for key.
    if (!cacheResult) {

    result = joinPoint.proceed() // Execute annotated caller method.

    if (result instanceof Response) {
    Response r = (Response) result
    String responseJson = r.getEntity().toString()
    logger.log(Level.INFO, "Cached response at " + requestUri)
    _cache.setCache(responseJson, requestUri.toString(), getTTL())
    } else {
    logger.log(Level.WARNING, "JoinPoint method does not return javax.ws.rs.core.Response no caching will be performed.")
    return null
    }

    } else {
    log.ino "Returned previously cached response at " + requestUri
    //result = Response.status(200).entity(cacheResult).build()
    }

    return result
    }
   */
}
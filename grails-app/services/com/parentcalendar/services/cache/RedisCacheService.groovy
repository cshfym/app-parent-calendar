package com.parentcalendar.services.cache

import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import redis.clients.jedis.Protocol


@Component
class RedisCacheService {

    def grailsApplication

    private static final log = LogFactory.getLog(this)

    private JedisPool pool

    public void setCache(String key, String data, int ttl) {

        Jedis jedis = getPool()?.getResource()

        if (!jedis) {
            return // Caching not available.
        }

        if (!key) {
            getPool().returnResource(jedis)
            return
        }

        if (!data) {
            getPool().returnResource(jedis)
            return
        }

        jedis.set(key, data)
        jedis.expire(key, ttl)
        log.info("Setting cache at key $key for $ttl.")
        getPool().returnResource(jedis)
    }

    public String getCache(String key) {

        Jedis jedis = getPool()?.getResource()

        if (!jedis) {
            return null // Caching not available.
        }

        if (!key) {
            getPool().returnResource(jedis)
            return null
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

    public void flushCache(String key) {

        Jedis jedis = getPool()?.getResource()

        if (!jedis) {
            return // Caching not available.
        }

        if (!key) {
            getPool().returnResource(jedis)
            return
        }

        jedis.del(key)
        log.info("Cache flushed for key $key")

        getPool().returnResource(jedis)

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

}
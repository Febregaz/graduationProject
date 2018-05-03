package yuzhaoLiu.project.redis;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {
    private static final Logger logger = Logger.getLogger(RedisCache.class);
    private static JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    private final String id;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("require an ID");
        }
        logger.debug("RedisCache:id=" + id);
        this.id = id;
    }

    public void clear() {
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            connection.flushDb();
            connection.flushAll();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public String getId() {
        return this.id;
    }

    public Object getObject(Object key) {
        Object result = null;
        RedisConnection connection = null;
        try {
            jedisConnectionFactory.afterPropertiesSet();
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = serializer.deserialize(connection.get(serializer.serialize(key)));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                //connection.close();
            }
        }
        return result;
    }

    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    public int getSize() {
        int result = 0;
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public void putObject(Object key, Object value) {
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Object removeObject(Object key) {
        RedisConnection connection = null;
        Object result = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = connection.expire(serializer.serialize(key), 0);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }
}

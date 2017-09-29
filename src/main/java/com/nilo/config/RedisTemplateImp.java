package com.nilo.config;

import com.nilo.utils.JacksonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/22.
 */

@Service
public class RedisTemplateImp  implements RedisCachez{

    private static final Logger log = LoggerFactory.getLogger(RedisTemplateImp.class);

        @Autowired
        private RedisTemplate<String, ?> redisTemplate;

        @Override
        public boolean set(final String key, final String value) {
            boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    connection.set(serializer.serialize(key), serializer.serialize(value));
                    return true;
                }
            });
            return result;
        }

    @Override
    public boolean set(final String key, final String value, final int expire) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.setEx(serializer.serialize(key),expire, serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    public String get(final String key){
            String result = redisTemplate.execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    byte[] value =  connection.get(serializer.serialize(key));
                    return serializer.deserialize(value);
                }
            });
            return result;
        }

    @Override
    public Map<String, Object> hgetAll(final String key) {
        Map result = redisTemplate.execute(new RedisCallback<Map>() {
            @Override
            public Map doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                RedisSerializer serializer1 = redisTemplate.getDefaultSerializer();
                Map<byte[], byte[]> value =  connection.hGetAll(serializer.serialize(key));
                Map<String, Object> result = new HashMap<String, Object>();
                for(Map.Entry<byte[], byte[]> entry : value.entrySet()) {
                    result.put(serializer.deserialize(entry.getKey()), serializer1.deserialize(entry.getValue()));
                }
                return result;
            }
        });
        return result;
    }


    @Override
        public boolean expire(final String key, long expire) {
            return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }

        @Override
        public <T> boolean setList(String key, List<T> list) {
            String value = JacksonUtil.toJson(list);
            return set(key,value);
        }

        @Override
        public <T> List<T> getList(String key,Class<T> clz) {
            String json = get(key);
            if(json!=null){
                List<T> list = JacksonUtil.jsonArray2List(json);
                return list;
            }
            return null;
        }

        @Override
        public long lpush(final String key, Object obj) {
            final String value = JacksonUtil.toJson(obj);
            long result = redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
                    return count;
                }
            });
            return result;
        }

        @Override
        public long rpush(final String key, Object obj) {
            final String value = JacksonUtil.toJson(obj);
            long result = redisTemplate.execute(new RedisCallback<Long>() {
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
                    return count;
                }
            });
            return result;
        }

        @Override
        public String lpop(final String key) {
            String result = redisTemplate.execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    byte[] res =  connection.lPop(serializer.serialize(key));
                    return serializer.deserialize(res);
                }
            });
            return result;
        }

		@Override
		public void delete(String key) {
			if (exists(key)) {
	            redisTemplate.delete(key);
	        }
		}

		@Override
		public boolean exists(String key) {
	        return redisTemplate.hasKey(key);
		}

    }

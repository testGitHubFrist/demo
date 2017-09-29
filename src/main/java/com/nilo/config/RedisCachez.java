package com.nilo.config;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/22.
 */
public interface RedisCachez {

    public boolean set(String key, String value);

    public boolean set(String key, String value, int expire);

    public String get(String key);

    public Map hgetAll(String key);

    public boolean expire(String key,long expire);

    public <T> boolean setList(String key ,List<T> list);

    public <T> List<T> getList(String key,Class<T> clz);

    public long lpush(String key,Object obj);

    public long rpush(String key,Object obj);

    public String lpop(String key);
    
    public void delete(String key);
    
    public boolean exists(String key);
}

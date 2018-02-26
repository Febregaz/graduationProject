package yuzhaoLiu.project.redis;

import redis.clients.jedis.Jedis;

public class RedisUtil {
    private Jedis jedis;
    private String url = "127.0.0.1";
    public RedisUtil(){
        jedis = new Jedis(url);
    }

    public void setDemo(String key,String value){
        jedis.set(key,value);
    }

    public String getDemo(String key){
        String value = jedis.get(key);
        return value;
    }
}

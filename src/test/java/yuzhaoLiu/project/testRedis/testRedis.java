package yuzhaoLiu.project.testRedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import yuzhaoLiu.project.redis.RedisUtil;

public class testRedis {
    private RedisUtil redisUtil = new RedisUtil();

    @Test
    public void testRedis(){
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setDemo("test","I Love You !");
        System.out.print(redisUtil.getDemo("test"));
    }
}

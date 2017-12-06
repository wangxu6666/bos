package bos_fore;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**  
 * ClassName:TestRedis <br/>  
 * Function:  <br/>  
 * Date:     Dec 6, 2017 3:38:46 PM <br/>       
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestRedis {
    @Autowired
    private RedisTemplate<String, String> template;
    @Test
    public void test1() {
        template.opsForValue().set("name", "zhangsan");
    }
    
    @Test
    public void test2() {
        template.opsForValue().set("age", "26",8, TimeUnit.SECONDS);
    }

}
  

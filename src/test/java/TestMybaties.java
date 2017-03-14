import com.alibaba.fastjson.JSON;
import com.zs.pojo.User;
import com.zs.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by zouxiang on 2017/3/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMybaties {

    private static Logger logger = Logger.getLogger(TestMybaties.class);
    //  private ApplicationContext ac = null;
    @Resource
    private UserService userService = null;

//  @Before
//  public void before() {
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//      userService = (IUserService) ac.getBean("userService");
//  }

    @Test
    public void test1() {
        User user = userService.getUserById(1);
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        logger.info(JSON.toJSONString(user));
    }
    @Test
    public void testUserList(){
        List<User> users = userService.findList();
        logger.info(users.size());
        assertEquals(4,users.size());
    }

    @Test
    public void testRedis(){
        User user1 = userService.getUserById(1);
        logger.info(user1.getUserName());
        userService.rename(1,"zouxiang12367888999");
        User user2 = userService.getUserById(1);
        logger.info(user2.getUserName());
    }
    @Test
    public void testSaveRedis(){
        List<User> users = userService.findList();
//        assertEquals(4,users.size());
        User user = new User("测试2","pass",12);
        userService.saveUser(user);
        List<User> users2 = userService.findList();
//        assertEquals(4,users2.size());
    }

}

import com.zs.controller.UserController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Created by zouxiang on 2017/3/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-mybatis.xml","classpath*:spring-mvc.xml"})
public class TestMyBatiesController {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Resource
    private UserController userController;
    @Before
    public void setUp(){
        System.out.println("setup");
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void testLogin() {
        try {
            request.setParameter("userName111222", "admin");
            request.setParameter("password", "2");
            assertEquals("login",userController.loginTest(request,response)) ;
            request.setParameter("id","1");
            ModelMap modelMap = new ModelMap();
            String s = userController.toIndex(request,modelMap);
            System.out.println(s);
            assertEquals("1",userController.test(request));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        System.out.println("tearDown");
    }
}

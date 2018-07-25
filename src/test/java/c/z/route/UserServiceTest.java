/**
 * 
 */
package c.z.route;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import c.z.bizservice.UserService;

/**
 * @author zhuzhong
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring/spring-application.xml" })
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        String ret = userService.getUserOrder(2000L, "btest");
        System.out.println(ret);
    }
}

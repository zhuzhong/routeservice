package c.z.route;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import c.z.bizservice.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring/spring-application.xml" })
public class OrderServiceTest {

    @Autowired
    @Qualifier("c.z.bizservice.OrderService")
    private OrderService orderService;

    @Test
    public void test() {
        String result = orderService.queryOrderTrack(100L, "atest");
        System.out.println(result);
    }
}

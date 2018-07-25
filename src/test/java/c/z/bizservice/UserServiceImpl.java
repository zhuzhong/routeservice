/**
 * 
 */
package c.z.bizservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author zhuzhong
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("c.z.bizservice.OrderService")
    private OrderService orderService;

    @Override
    public String getUserOrder(Long orderId, String sellerCode) {
        // TODO Auto-generated method stub
        return orderService.queryOrderTrack(orderId, sellerCode);
    }

}

/**
 * 
 */
package c.z.bizservice;

import org.springframework.stereotype.Service;

/**
 * @author zhuzhong
 *
 */
@Service("atestOrderService")
public class AtestOrderServiceImpl implements OrderService {

    @Override
    public String queryOrderTrack(Long orderId, String sellerCode) {
        // TODO Auto-generated method stub
        return "from a " + orderId;
    }

}

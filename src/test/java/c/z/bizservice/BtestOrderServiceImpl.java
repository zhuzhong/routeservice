package c.z.bizservice;

import org.springframework.stereotype.Service;

@Service("btestOrderService")
public class BtestOrderServiceImpl implements OrderService {

    @Override
    public String queryOrderTrack(Long orderId, String sellerCode) {
        // TODO Auto-generated method stub
        return "from B " + orderId;
    }

}

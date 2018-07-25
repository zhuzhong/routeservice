/**
 * 
 */
package c.z.bizservice;

import c.z.route.RouteParam;
import c.z.route.RouteService;

/**
 * @author zhuzhong
 *
 */
@RouteService
public interface OrderService {

    String queryOrderTrack(Long orderId, @RouteParam String sellerCode);
}

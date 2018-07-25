/**
 * 相应的业务bean，需要使用RouteService注解
 * ,该接口中的每一个业务方法都需要有路由参数，并且路由参数需要用RouteParam注解.
 * 
 * 
 * 被代理的接口实现类为用户使用的。
 * 由于在一个ApplicationContext上下文中，有多个关于该接口的实现类(一般情况下至少是3个），
 * 而用户需要使用的则是由代理接口实现的，它在ApplicationContext中的名字为为全接口(名包名+接口名)
 */
package c.z.route;
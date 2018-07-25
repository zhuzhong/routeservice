/**
 * 
 */
package c.z.route;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;

/**
 * @author zhuzhong
 * @param <T>
 *
 */
public class RouteServiceFactoryBean<T> implements FactoryBean<T>, InvocationHandler// ,
                                                                                    // ApplicationContextAware
{

    private Class<T> mapperInterface;
    private ApplicationContext context;
 
    public RouteServiceFactoryBean() {

    }

    public RouteServiceFactoryBean(Class<T> mapperInterface, ApplicationContext context) {
        this.mapperInterface = mapperInterface;
        this.context = context;
    }

     @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, this);
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    
    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    
    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String routeCode = null;
        Annotation[ /* 参数个数索引 */][ /* 注解个数索引 */ ] paramsAnno = method.getParameterAnnotations();
        if (paramsAnno != null) {
            for (int i = 0; i < paramsAnno.length; i++) {
                if (paramsAnno[i].length > 0) {
                    routeCode = (String) args[i]; // 获取到路由的参数值
                    break;
                }
            }
        }

        return method.invoke(context.getBean(genBeanName(routeCode, mapperInterface.getSimpleName()), mapperInterface),
                args);

    }

    private String genBeanName(String sellerCode, String interfaceSimpleName) {
        return new StringBuilder(sellerCode.toLowerCase()).append(interfaceSimpleName).toString();
    }

   /* @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

    }*/
    
}

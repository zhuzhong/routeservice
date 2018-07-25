package c.z.route;

import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

public class RouteScannerConfigurer
        implements BeanDefinitionRegistryPostProcessor, InitializingBean, ApplicationContextAware {

    
    private static final Logger logger=LoggerFactory.getLogger(RouteScannerConfigurer.class);
    private String basePackage;
    private Class<? extends Annotation> annotationClass;
    private ApplicationContext context;
    private BeanNameGenerator nameGenerator=new BeanNameGeneratorImpl();

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        this.context = applicationContext;
    }

    
    public void setNameGenerator(BeanNameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }
    
    public BeanNameGenerator getNameGenerator() {
        return nameGenerator;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //System.out.println("beanFactory.....");
        //do nothing
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //System.out.println("ttt");
        
        if(basePackage==null){
            logger.error("basepackage is null");
            throw new RuntimeException("basepackage is null");
            
        }
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathRouteServiceScanner scanner = new ClassPathRouteServiceScanner(registry);
        scanner.setAnnotationClass(this.annotationClass);
         scanner.setApplicationContext(context);
        scanner.setResourceLoader(context);
        scanner.setBeanNameGenerator(this.nameGenerator);
        scanner.registerFilters();
        scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage,
                ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));

    }

   
}
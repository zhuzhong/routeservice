/**
 * 
 */
package c.z.route;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

/**
 * @author zhuzhong
 *
 */
public class BeanNameGeneratorImpl implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        // TODO Auto-generated method stub
        return definition.getBeanClassName();
    }

}

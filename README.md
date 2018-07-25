# 内部服务路由的实现


##  问题
对于一个业务接口服务它可以有多个实现类。对于使用者来说，需要指定它所使用的是哪一个实现。比如一个接口：
	
	interface DemoService{
		void printStr(String str);
	}
	
	@Service("printerService")
	class PrinterServiceImpl implements DemoService{
		public void printStr(String str){
			System.out.println("use printer print"+str);
		}
	}
	@Service("copyerService")
	class CopyerServiceImpl implements DemoService{
		public void printStr(String str){
			System.out.println("use copyer print"+str);
		}
	}
	
（比如上述的接口对应的一个业务场景发短信，131，132，133走联通的通道发出去； 136，137，139走移动的通道发出去；等等业务情形）

当我们在业务类中使用这个上述接口时，需要明确的指定我们需要使用的是哪一个实现类接口；对于代码多数情形定义如下：

	public class BizClazz implements BizInterface{
	
		@Autowired
		@Qualifier("printerService")
		private DemoService printerService;
		
		@Autowired
		@Qualifier("copyerService")
		private DemoService copyerService;
		
		public void bizMethod(String str,String routeCode){
			if("copyer".equals(routeCode){
				copyerService.printStr(Str);
			}else{
				printerService.println(Str);
			}
		
		}
	}
	

如果按照这个思路，业务代码中处处都是这样的if else 代码，那么能否解决掉这样的代码呢？答案是肯定的。对于服务实现类的路由，在java中最好的解决办法即为代理+反射。

## 解决方案
我们重新定义接口

	package c.z.route;
	
	@RouteService  //接口增加注解
	interface DemoService{
		void printStr(String str,@RouteParam String routeCode/*路由参数增加注解*/);
	}
	
	@Service("printerDemoService")/*命名规则为 routeCode 小写+接口的短名 */
	class PrinterServiceImpl implements DemoService{
		public void printStr(String str,String routeCode){
			System.out.println("use printer print"+str);
		}
	}
	@Service("copyerDemoService")
	class CopyerServiceImpl implements DemoService{
		public void printStr(String str ,String routeCode){
			System.out.println("use copyer print"+str);
		}
	}
	
	而相应的业务代码变更为：
	
	public class BizClazz implements BizInterface{
	
		@Autowired
		@Qualifier("c.z.route.DemoService")/*接口代理实现的命名规则默认为：接口的全称*/  
		private DemoService demoService;
		
		public void bizMethod(String str,String routeCode){
				demoService.printStr(str,routeCode);		
		}
	}

jar依赖
	
		<dependency>
			<groupId>c.z</groupId>
			<artifactId>routeservice</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>

	
spring配置

	<bean class="c.z.route.RouteScannerConfigurer">
		<property name="annotationClass" value="c.z.route.RouteService" />
		<property name="basePackage" value="c.z.bizservice" />
	</bean>

## 技术要点

本jar主要抄写mybatis-spring的思路及大部分代码。即对于任一个满足条件（注解）的接口进行代理，提供给用户使用。而真正的实现，则可以只关注一个方面，从而避免了太多的判断条件。


|业务接口|命名规范|实现者|使用者
|-------|-------|-----|-----|
|c.z.route.DemoService|接品全称：c.z.route.DemoService|框架代理实现|业务接口消费者|
|c.z.route.DemoService|routeCode小写+接口SimpleName:printerDemoService|真实业务提供者实现|框架代理|
|c.z.route.DemoService|routeCode小写+接口SimpleName:copyerDemoService|真实业务提供者实现|框架代理|
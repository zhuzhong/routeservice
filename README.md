# 内部服务路由的实现


##  问题
对于一个业务接口服务它可以有多个实现类。对于使用者来说，需要指定它所使用的是哪一个实现。比如一个接口：
	
	interface DemoService{
		void printStr(String str);
	}
	
	@service("printerService")
	class PrinterServiceImpl implements DemoService{
		public void printStr(String str){
			System.out.println("use printer print"+str);
		}
	}
	@service("copyerService")
	class CopyerServiceImpl implements DemoService{
		public void printStr(String str){
			System.out.println("use copyer print"+str);
		}
	}
	
（比如上述的接口对应的一个业务场景发短信，131，132，133走联通的通道发出去； 136，137，139走移动的通道发出去；等等业务情形）

当我们在业务类中使用这个上述接口时，需要明确的指定我们需要使用的是哪一个实现类接口；对于代码多数情形定义如下：

	public class BizClazz implements BizInterface{
	
		@autowire
		@Qualifier("printerService")
		private DemoService printerService;
		
		@autowire
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
	
	@service("printerDemoService")/*命名规则为 routeCode+接口的短名 */
	class PrinterServiceImpl implements DemoService{
		public void printStr(String str,String routeCode){
			System.out.println("use printer print"+str);
		}
	}
	@service("copyerDemoService")
	class CopyerServiceImpl implements DemoService{
		public void printStr(String str ,String routeCode){
			System.out.println("use copyer print"+str);
		}
	}
	
	而相应的业务代码变更为：
	
	public class BizClazz implements BizInterface{
	
		@autowire
		@Qualifier("c.z.route.DemoService")/*接口代理实现的命名规则默认为：接口的全称*/  
		private DemoService demoService;
		
		public void bizMethod(String str,String routeCode){
				demoService.printStr(str,routeCode);		
		}
	}
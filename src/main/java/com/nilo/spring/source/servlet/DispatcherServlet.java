package com.nilo.spring.source.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nilo.spring.source.annotation.Controller;
import com.nilo.spring.source.annotation.RequestMapping;
import com.nilo.spring.source.annotation.Service;
import com.nilo.spring.source.annotation.Quatifier;
/**
 * 
 * @author GaoQun
 * DispatcherServlet :前端控制器
 */
public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 存储扫描的包名
	List<String> packageNames = new ArrayList<String>();
	// 所有类的实例，key是注解的value,value是所有类的实例
	Map<String, Object> instanceMap = new HashMap<String, Object>();
	// 存储建立一个映射关系（地址映射，不同的地址映射到不同的方法）：
	Map<String, Object> handerMap = new HashMap<String, Object>();

	public DispatcherServlet() {
		super();
	}

	
	/**
	 * 初始化 1、扫描包；2、类的实例化；3、建立映射关系HandlerMapping获取method映射器；4、注入就是controller注入service
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		//1、包的扫描，扫描application.xml中的配置
		scanPackage("com.nilo.spring.source");//这里写死，实际上ApplicationContext.xml
		//2、类的加载，实例化过程
		try {
			filterAndInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//3、handlerMapping建立URL与Controller的对应关系
		handlerMapping();
		//4、依赖注入，例如Controller依赖service
		ioc();  
	}



	


	/**
	 * 1、 扫描包下的所有文件
	 * @param string
	 */
	private void scanPackage(String Package) {
		//将所有的.转义获取对应的路径;实际上是com.nilo.spring.source 转换为file://com/nilo/spring/source目录加载文件
		URL url=this.getClass().getClassLoader().getResource("/"+replaceTo(Package));
		String pathFile =url.getFile();//文件路径 file://com/nilo/spring/source
		File file=new File(pathFile);
		String fileList[]=file.list();//获取所有的文件路径
		//循环加入packageNames 集合，等待实例化
		for (String path : fileList) {
			File eachFile = new File(pathFile + path);
			 if (eachFile.isDirectory()) {  
				    //递归
	                scanPackage(Package + eachFile.getName());  
            } else {  
                packageNames.add(Package + "." + eachFile.getName());  
            }  
		}
	}

    /**
     * 实际上是com.nilo.spring.source 转换为file://com/nilo/spring/source
     * @param path
     * @return
     */
	private String replaceTo(String path) {  
	        return path.replaceAll("\\.", "/");  
	} 

    /**
     * 2、类的实例化；通过反射实例化类，spring MVC 单例模式：分为饱汉与饿汉
     * @throws ClassNotFoundException 
     */
	private void filterAndInstance() throws Exception {
		//判断扫描的包集合是否为空
		if(packageNames.size()<0){
			return;
		}
		for (String className : packageNames) {
			Class<?> cName=Class.forName((className.replaceAll(".class", "").trim()));
			//判断注解是否为Controller
			if(cName.isAnnotationPresent(Controller.class)){
				Object instance=cName.newInstance();
				Controller controller = (Controller) cName.getAnnotation(Controller.class);
				String key = controller.value(); //controller注解的名称
				instanceMap.put(key, instance);
			}else if(cName.isAnnotationPresent(Service.class)){
				Object instance = cName.newInstance();  
                Service service = (Service) cName.getAnnotation(Service.class);  
                String key = service.value();  
                instanceMap.put(key, instance);  
			}else{
				continue;
			}
		}
	}
	
	/**
	 * 3、建立映射关系
	 */
	private void handlerMapping() {
		//判断实例化集合是否为空；不为空是建立实例化id与RequestMapping的关系存入handlerMap中，访问时根据url找对应的Controller
		if(instanceMap.size()<0){
			return;
		}
		//遍历instanceMap中的Controller
		for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
			//查找所有的Controller类
			if (entry.getValue().getClass().isAnnotationPresent(Controller.class)) {
				Controller controller = (Controller) entry.getValue().getClass().getAnnotation(Controller.class);
				String ctvalue = controller.value();//Controller注解的名称
				Method[] methods = entry.getValue().getClass().getMethods(); //获取类的所有的方法
				//遍历所有的RequestMapping注解的方法
				for (Method method : methods) { 
					if (method.isAnnotationPresent(RequestMapping.class)) {  
						RequestMapping rm = (RequestMapping) method.getAnnotation(RequestMapping.class);
						String rmvalue = rm.value(); //注解的值
						handerMap.put("/" + ctvalue + "/" + rmvalue, method);  //例如 map.put(/demo/url,queryTest())
					}else{
						continue;
					}
				}
			}else{
				continue;
			}
		}
	}
	
	/**
	 * 4、注入
	 */
	private void ioc() {
		if (instanceMap.isEmpty()){
			return;
		}
		for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
			// 拿到里面的所有属性
			Field fields[] = entry.getValue().getClass().getDeclaredFields();
			for (Field field : fields) { 
				field.setAccessible(true);// 可访问私有属性
				//判断是否有注解
				if (field.isAnnotationPresent(Quatifier.class)){
					Quatifier quatifier = field.getAnnotation(Quatifier.class);
					String value = quatifier.value();
					field.setAccessible(true);
					try {  
	                    field.set(entry.getValue(), instanceMap.get(value));  
	                } catch (IllegalArgumentException e) {  
	                    e.printStackTrace();  
	                } catch (IllegalAccessException e) {  
	                    e.printStackTrace();  
	                }  
				}
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 this.doGet(req, resp);  
	}

	/**
	 * 执行post方法时
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取url  demo/url
		String url = req.getRequestURI();
		String context = req.getContextPath();
		String path = url.replace(context, ""); 
		//根据url找到类的方法queryTest()
		Method method = (Method) handerMap.get(path); 
		Class<?> controller = (Class<?>) instanceMap.get(path.split("/")[1]); 
		try {
			//执行方法
			method.invoke(controller, new Object[] { req, resp, null });
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	

}

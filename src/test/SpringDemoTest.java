import com.alibaba.druid.pool.DruidDataSource;
import com.mark.project.springDemo.day01.domain.*;
import com.mark.project.springDemo.day01.domain.SomeBean;
import com.mark.project.springDemo.day02.auto_wire_method.auto_wire.*;
import com.mark.project.springDemo.day02.auto_wire_method.resource.Test1;
import com.mark.project.springDemo.day02.dynamicProxy.JDKProxy;
import com.mark.project.springDemo.day02.iocAnno.AnnoTest;
import com.mark.project.springDemo.day02.staticProxy.domain.Person;
import com.mark.project.springDemo.day02.staticProxy.service.IPersonService;
import com.mark.project.springDemo.day02.staticProxy.service.impl.PersonServiceImpl;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.misc.ProxyGenerator;

import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mark_Yan on 2017/8/1.
 *
 * SpringDemo测试类
 *
 */
//告诉JVM Spring运行在JUnit上
@RunWith(SpringJUnit4ClassRunner.class)
//表示要去哪个路径下加载哪一个配置的文件
@ContextConfiguration({"classpath:spring/helloworld.xml", "classpath:spring/Properties.xml",
		               "classpath:spring/datasource.xml",
		               "classpath:spring/aopConfig.xml"})
public class SpringDemoTest {
//	@Autowired //自动装配 先会根据类型去到配置文件中到对应的类，如果没有找到就会根据名字查找
//	HelloWorld helloWorld = null;
	@Autowired
	ApplicationContext ctx;

	@Test
	public void testHelloWorld() {
		Resource resource = new ClassPathResource("applicationContext.xml"); //实例化资源加载类
		BeanFactory factory = new XmlBeanFactory(resource); //实例化一个xml工厂对象
		HelloWorld helloWorld = factory.getBean(HelloWorld.class); //获取对应的实体类
		helloWorld.say();
	}

	//模拟spring加载类
	@Test
	public void testMock() throws Exception {
		//解析xml文件得到class property中的name和value
		String className = "com.mark.project.springDemo.day01.domain.HelloWorld";
		String proName = "name";
		String proValue = "Mark";
		//反射机制获取类
		Class<?> clz = Class.forName(className);
		Object obj = clz.newInstance(); //获取一个对象
		//使用内省机制来获取类中的属性和方法
		BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);//从clz类开始 到父类Object停止
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();//获取类中的属性描述
		for ( PropertyDescriptor pd : pds ) {
			if ( proName.equals(pd.getName()) ) {
				//判断类中的属性与解析xml中的一致
				pd.getWriteMethod().invoke(obj, proValue); //获取对应的可写方法,将value值写入到对象中
			}
		}
		HelloWorld helloWorld = (HelloWorld) obj;
		helloWorld.say();
	}

//	@Test
//	public void testSpringTest() {
//		helloWorld.say();
//	}

	@Test
	public void testConstructor() {
		HelloWorld hw = ctx.getBean("helloWorld", HelloWorld.class);
		System.out.println(hw);
	}

	@Test
	public void testStaticFactory() {
		SomeBean sb = ctx.getBean("someBean", SomeBean.class);
		System.out.println(sb);
	}


	@Test
	public void testInstanceFactory() {
		SomeBean sb = ctx.getBean("someBean1", SomeBean.class);
		System.out.println(sb);
	}

	@Test
	public void testFactoryBean() {
		SomeBean sb = ctx.getBean("someBean2", SomeBean.class);
		System.out.println(sb);
	}

	@Test
	public void testSetterProperties() {
		Employee e = ctx.getBean("employee", Employee.class);
		System.out.println(e);
	}

	@Test
	public void testSetterObj() {
		Student student = ctx.getBean("student", Student.class);
		System.out.println(student);
	}

	@Test
	public void testCollection(){
		CollectionBean collectionBean = ctx.getBean("collectionBean", CollectionBean.class);
		System.out.println(collectionBean);
	}

	@Test
	public void testDataSource() throws Exception {
		DataSource ds = ctx.getBean("source", DruidDataSource.class);
		Connection conn = ds.getConnection();
		ResultSet rs = conn.prepareStatement("SELECT * FROM t_employee").executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("name"));
		}
		rs.close();
		conn.close();
	}


	@Test
	public void testAutowired() {
		com.mark.project.springDemo.day02.auto_wire_method.auto_wire.SomeBean someBean
				= ctx.getBean("someBean", com.mark.project.springDemo.day02.auto_wire_method.auto_wire.SomeBean.class);
		System.out.println(someBean);
	}

//	@javax.annotation.Resource
//	Test1 test1;
//
//	@Test
//	public void testResource() {
//		System.out.println(test1);
//	}

	@Test
	public void testIoC() {
		AnnoTest test = ctx.getBean("annoTest", AnnoTest.class);
		test.doWork();
	}

//	//由于我们增加了代理 并且代理也实现了IPersonService接口 根据面向接口编程 我们要让接口注入的实现类是PersonServiceStaticProxyImpl
//	@Autowired
//	@Qualifier("serviceStaticProxy") //根据类型和名字去查找 这样就会找到PersonServiceStaticProxyImpl
//	private IPersonService personServiceImpl;
//
//
//	/**
//	 * 1.在PersonServiceImpl类中,给DAO和TxManager提供Setter方法,但是配置文件中不对其配置property,执行测试不报错.
//	 */
//	@Test
//	public void testStaticProxy() {
//		//IPersonService personService = ctx.getBean("personServiceImpl", PersonServiceImpl.class);
//		personServiceImpl.save(new Person("Mark"));
//	}
//
//	@Autowired
//	private JDKProxy proxy;
//	@Test
//	public void testDynamicProxy() {
//		IPersonService personService = proxy.getObject();
//		personService.save(new Person("Tom"));
//		saveProxyClass("D:/proxy.class", personService.getClass().getName(), personService.getClass().getInterfaces()); //保存创建出来的动态代理类
//	}

	public static boolean saveProxyClass(String path, String proxyClassName, Class[] interfaces) {
		if (proxyClassName == null || path == null) {
			return false;
		}

		// get byte of proxy class
		byte[] classFile = ProxyGenerator.generateProxyClass(proxyClassName, interfaces);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			out.write(classFile);
			out.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}


	@Autowired
	private IPersonService personService;
	@Test
	public void testAOP() {
		//personService.save(new Person("Lucy"));
		personService.update(new Person("Tim"));
	}



}

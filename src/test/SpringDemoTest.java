import com.mark.project.springDemo.day01.domain.HelloWorld;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Created by Mark_Yan on 2017/8/1.
 *
 * SpringDemo测试类
 *
 */
public class SpringDemoTest {


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

}

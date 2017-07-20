import com.mark.project.hibernateDemo.dao.IUserDAO;
import com.mark.project.hibernateDemo.dao.impl.UserDAOImpl;
import com.mark.project.hibernateDemo.domain.Employee;
import com.mark.project.hibernateDemo.domain.User;
import com.mark.project.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/18.
 *
 * 测试
 */
public class HibernateDemoTest {

	private IUserDAO userDAO = new UserDAOImpl();

	@Test
	public void save() {

		User u = new User();
		u.setName("Tom");
		u.setSalary(BigDecimal.valueOf(800.00));
		u.setHiredate(new Date());
		userDAO.save(u);

	}

	@Test
	public void delete() {
		userDAO.delete(4L);
	}

	@Test
	public void update() {
		User user = userDAO.get(1L);
		user.setName("Lily");
		userDAO.update(user);
	}

	@Test
	public void get() {
		User user = userDAO.get(1L);
		System.out.println(user);
	}

	@Test
	public void list() {
		List<User> users = userDAO.list();
		for( User user : users ) {
			System.out.println(user);
		}
	}


	// 测试事务模板
	@Test
	public void testTransactionTemplate() {
		User u = new User();
		Session session = null;
		try {
			session = HibernateUtil.getSeesion();
			session.beginTransaction();
			session.save(u); //具体操作 修改成具体逻辑代码
			session.getTransaction().commit();
		} catch (Exception e) {
			// 发生异常后要回滚
			if ( session != null && session.getTransaction().isActive() ) { //session不为空并且事务是活动状态
				session.getTransaction().rollback();
			}
		} finally {
			if ( session != null && session.isOpen() ) {
				session.close();
			}
		}
	}

	@Test
	public void testSaveEmployee() {
		Employee employee = new Employee();
		employee.setName("Mark");
		Session session = null;
		try {
			session = HibernateUtil.getSeesion();
			session.beginTransaction();
			session.save(employee); //具体操作 修改成具体逻辑代码
			session.getTransaction().commit();
		} catch (Exception e) {
			// 发生异常后要回滚
			if ( session != null && session.getTransaction().isActive() ) { //session不为空并且事务是活动状态
				session.getTransaction().rollback();
			}
		} finally {
			if ( session != null && session.isOpen() ) {
				session.close();
			}
		}
	}

}

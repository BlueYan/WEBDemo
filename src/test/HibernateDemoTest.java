import com.mark.project.hibernateDemo.dao.IUserDAO;
import com.mark.project.hibernateDemo.dao.impl.UserDAOImpl;
import com.mark.project.hibernateDemo.domain.Employee;
import com.mark.project.hibernateDemo.domain.User;
import com.mark.project.hibernateDemo.mapping_relation.domain.Department;
import com.mark.project.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/18.
 * <p>
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
		for (User user : users) {
			System.out.println(user);
		}
	}


	// 测试事务模板
	@Test
	public void testTransactionTemplate() {
		User u = new User();
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(u); //具体操作 修改成具体逻辑代码
			session.getTransaction().commit();
		} catch (Exception e) {
			// 发生异常后要回滚
			if (session != null && session.getTransaction().isActive()) { //session不为空并且事务是活动状态
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
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
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(employee); //具体操作 修改成具体逻辑代码
			session.getTransaction().commit();
		} catch (Exception e) {
			// 发生异常后要回滚
			if (session != null && session.getTransaction().isActive()) { //session不为空并且事务是活动状态
				session.getTransaction().rollback();
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Test
	public void testTransient1() {
		Employee employee = new Employee();
		employee.setName("Tim"); //此时对象处于临时状态
		Session session = HibernateUtil.getSession();
		session.save(employee);
		session.close();
	}

	@Test
	public void testTransient2() {
		Employee employee = new Employee();
		employee.setId(1L); //此时对象处于游离状态
		Session session = HibernateUtil.getSession();
		session.delete(employee);
		session.close();
		System.out.println("id = " + employee.getId());
	}


	/**
	 * 情况1：先保存one方，再保存many方。
	 * 这种情况我们会发现sql语句只有三条
	 * Hibernate: insert into dept (name) values (?)
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * 原因：因为我们的Employee对象依赖于Department对象。所以Department对象存在的话，那么我们就可以直接对Employee执行插入语句。
	 *
	 * 情况2：先保存many方，再保存one方。(不推荐使用，效率低)
	 * 这种情况会发送五条sql语句。
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: insert into dept (name) values (?)
	 * Hibernate: update employee set ename=?, dept_id=? where eid=?
	 * Hibernate: update employee set ename=?, dept_id=? where eid=?
	 * 原因：因为依赖的关系，我们先保存Employee对象的时候并不知道Department的id值，所以一开始会默认为空。当我们保存了Department对象后，发现
	 * department存在，检查都两者数据不一致，就会发送更新的sql语句进行更新.
	 */
	@Before
	public void testMappingRelationSave() {

		com.mark.project.hibernateDemo.mapping_relation.domain.Employee e1 = new com.mark.project.hibernateDemo.mapping_relation.domain.Employee();
		e1.setName("张三");
		com.mark.project.hibernateDemo.mapping_relation.domain.Employee e2 = new com.mark.project.hibernateDemo.mapping_relation.domain.Employee();
		e2.setName("李四");


		Department department = new Department();
		department.setName("开发部");

		e1.setDept(department);
		e2.setDept(department);

		//保存操作
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(department);
		session.save(e1);
		session.save(e2);
		session.getTransaction().commit();
		session.close();
	}


	/**
	 * 1. 当我们执行e.getDept().getName()的时候，才会发送sql语句进行查询。此处使用了懒加载。
	 * 2. 由于使用了懒加载，所以必须要在session.close()之前调用，否则会报no-session的错误
	 * 3. 由于get()方法返回的对象可能为NULL。所以可以使用if-null来判读时候存在值。
	 */
	@Test
	public void testMappingRelationGet() {
		Session session = HibernateUtil.getSession();
		com.mark.project.hibernateDemo.mapping_relation.domain.Employee e = (com.mark.project.hibernateDemo.mapping_relation.domain.Employee)
				session.get(com.mark.project.hibernateDemo.mapping_relation.domain.Employee.class, 1L);
		System.out.println(e);

		Department dept = e.getDept();

		session.close();

		if ( dept == null ) {
			System.out.println("dept is null");
		} else {
			System.out.println(dept);
		}
	}

}

import com.mark.project.hibernateDemo.combination_relation.domain.SaleBill;
import com.mark.project.hibernateDemo.combination_relation.domain.SaleBillItem;
import com.mark.project.hibernateDemo.dao.IUserDAO;
import com.mark.project.hibernateDemo.dao.impl.UserDAOImpl;
import com.mark.project.hibernateDemo.domain.Employee;
import com.mark.project.hibernateDemo.domain.User;
import com.mark.project.hibernateDemo.inherit_relation.domain.Customer;
import com.mark.project.hibernateDemo.many2many.domain.Student;
import com.mark.project.hibernateDemo.many2many.domain.Teacher;
import com.mark.project.hibernateDemo.mapping_relation.domain.Department;
import com.mark.project.hibernateDemo.query.domain.EmployeeVO;
import com.mark.project.strut2.action.ParamAction;
import com.mark.project.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	 * <p>
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
	@Test
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

		if (dept == null) {
			System.out.println("dept is null");
		} else {
			System.out.println(dept);
		}
	}

	/**
	 * 多对一双向
	 * <p>
	 * 前提：未设置inverse="true"属性的情况下
	 * 1.先保存one方再保存many方
	 * 发现有五条sql语句。前面三条跟单向的many2one是一致的。而后面两条是由于Department也要维护和Employee的关系，它也会去更新Employee中
	 * 外键的值。所以会有两条update的语句，所以后面的两条update语句是没有必要的
	 * Hibernate: insert into dept (name) values (?)
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: update employee set dept_id=? where eid=?
	 * Hibernate: update employee set dept_id=? where eid=?
	 * map一级缓存(一个session对应一个map)
	 * -------------|------------------
	 * key         |  value
	 * ------------------------------
	 * Employee#1  |  e1(1L,"张三", 1L)
	 * -------------|------------------
	 * Department#1 |  dept(1L, "开发部")
	 * ---------------------------------
	 * 2.先保存many方再保存one方
	 * 发现有7条的sql语句。先保存employee，然后再保存department的数据，此时知道了dept_id的值，由于检查到右脏数据
	 * 就会发起update语句进行更新，update employee set ename=?, dept_id=? where eid=?。
	 * 但是由于one方也要维护数据，就会对employee发起更新数据，同样最后两条sql语句也是无用的。
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: insert into dept (name) values (?)
	 * Hibernate: update employee set ename=?, dept_id=? where eid=?
	 * Hibernate: update employee set ename=?, dept_id=? where eid=?
	 * Hibernate: update employee set dept_id=? where eid=?
	 * Hibernate: update employee set dept_id=? where eid=?
	 * <p>
	 * 设置inverse="true"
	 * 3.先保存one方再保存many方
	 * 发现跟单向的many2one发送的sql语句是一样的。因为在one方上设置了inverse="true"。表示将维护关系的权利转让给many方，自己不再维护。
	 * Hibernate: insert into dept (name) values (?)
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * 4.先保存many方再保存one方
	 * 发现也是跟单向的one2many一致
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: insert into employee (ename, dept_id) values (?, ?)
	 * Hibernate: insert into dept (name) values (?)
	 * Hibernate: update employee set ename=?, dept_id=? where eid=?
	 * Hibernate: update employee set ename=?, dept_id=? where eid=?
	 */
	@Test
	public void testMany2OneBidirectional() {
		com.mark.project.hibernateDemo.mapping_relation.domain.Employee e1 = new com.mark.project.hibernateDemo.mapping_relation.domain.Employee();
		e1.setName("张三");
		com.mark.project.hibernateDemo.mapping_relation.domain.Employee e2 = new com.mark.project.hibernateDemo.mapping_relation.domain.Employee();
		e2.setName("李四");

		Department department = new Department();
		department.setName("开发部");
		department.getEmployees().add(e1);
		department.getEmployees().add(e2);

		e1.setDept(department);
		e2.setDept(department);
		Session session = HibernateUtil.getSession();
		//如果数据库没有数据，一般有可能是没有提交事务
		session.beginTransaction();
		session.save(department);
		session.save(e1);
		session.save(e2);
		session.getTransaction().commit();
		session.close();
	}


	/**
	 * 保存操作
	 * 配置文件在one方中已经添加了cascade="all-delete-orphan"
	 * Hibernate: insert into t_sale_bill (saleDate) values (?)
	 * Hibernate: insert into t_sale_bill_item (name, price, num, sale_bill_id) values (?, ?, ?, ?)
	 * Hibernate: insert into t_sale_bill_item (name, price, num, sale_bill_id) values (?, ?, ?, ?)
	 * Hibernate: insert into t_sale_bill_item (name, price, num, sale_bill_id) values (?, ?, ?, ?)
	 * 保存one方就级联保存many方的数据。
	 */
	@Test
	public void testCombinationRelationSave() {

		//实例化单据实体类
		SaleBill saleBill = new SaleBill();
		saleBill.setSaleDate(new Date());

		//实例化三个明细实体类
		SaleBillItem item1 = new SaleBillItem();
		item1.setName("泡脚凤爪");
		item1.setPrice(new BigDecimal(5));
		item1.setNum(10);
		SaleBillItem item2 = new SaleBillItem();
		item2.setName("百威啤酒");
		item2.setPrice(new BigDecimal(10));
		item2.setNum(10);
		SaleBillItem item3 = new SaleBillItem();
		item3.setName("花生米");
		item3.setPrice(new BigDecimal(5));
		item3.setNum(5);

		saleBill.getBillItems().add(item1);
		saleBill.getBillItems().add(item2);
		saleBill.getBillItems().add(item3);

		item1.setSaleBill(saleBill);
		item2.setSaleBill(saleBill);
		item3.setSaleBill(saleBill);

		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(saleBill); //直接保存one方的数据 级联更新明细表中的数据
//		session.save(item1);
//		session.save(item2);
//		session.save(item3);
		session.getTransaction().commit();
		session.close();

	}


	/**
	 * 删除操作
	 * 先查询出来one方的数据
	 * Hibernate: select salebill0_.id as id1_0_0_, salebill0_.saleDate as saleDate2_0_0_ from t_sale_bill salebill0_ where salebill0_.id=?
	 * 再查询many方的数据
	 * Hibernate: select billitems0_.sale_bill_id as sale_bil5_0_0_, billitems0_.id as id1_1_0_, billitems0_.id as id1_1_1_, billitems0_.name
	 * as name2_1_1_, billitems0_.price as price3_1_1_, billitems0_.num as num4_1_1_, billitems0_.sale_bill_id as sale_bil5_1_1_
	 * from t_sale_bill_item billitems0_ where billitems0_.sale_bill_id=?
	 * <p>
	 * 删除操作：只删除one方数据的时候会级联删除many方的所有数据
	 * Hibernate: delete from t_sale_bill_item where id=?
	 * Hibernate: delete from t_sale_bill_item where id=?
	 * Hibernate: delete from t_sale_bill_item where id=?
	 * Hibernate: delete from t_sale_bill where id=?
	 * <p>
	 * 注意：删除操作session.delete(obj).此时的obj对象不能是new出来的，因为new出来的实体类是不存在many方的数据。
	 * 也就是存放many方的集合中是没有many对象的。所以只能是通过查询出来的对象，再去删除.
	 */
	@Test
	public void testCombinationRelationDelete() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		//从数据库中查询数据
		SaleBill saleBill = (SaleBill) session.get(SaleBill.class, 1L);
		//删除one方数据同时也会删除many方的数据
		session.delete(saleBill);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * Hibernate: insert into t_student (sName) values (?)
	 * Hibernate: insert into t_student (sName) values (?)
	 * Hibernate: insert into t_teacher (tName) values (?)
	 * Hibernate: insert into t_teacher (tName) values (?)
	 * Hibernate: insert into t_stu_teach (stu_id, teach_id) values (?, ?)
	 * Hibernate: insert into t_stu_teach (stu_id, teach_id) values (?, ?)
	 * Hibernate: insert into t_stu_teach (stu_id, teach_id) values (?, ?)
	 * Hibernate: insert into t_stu_teach (stu_id, teach_id) values (?, ?)
	 */
	@Test
	public void testMany2Many() {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Student stu1 = new Student();
		stu1.setSName("Mark");
		Student stu2 = new Student();
		stu2.setSName("Tom");
		Teacher t1 = new Teacher();
		t1.setTName("Lily");
		Teacher t2 = new Teacher();
		t2.setTName("Tim");
		stu1.getTeachers().add(t1);
		stu1.getTeachers().add(t2);
		stu2.getTeachers().add(t1);
		stu2.getTeachers().add(t2);
		t1.getStudents().add(stu1);
		t1.getStudents().add(stu2);
		t2.getStudents().add(stu1);
		t2.getStudents().add(stu2);
		session.save(stu1);
		session.save(stu2);
		session.save(t1);
		session.save(t2);
		session.getTransaction().commit();
	}

	/**
	 * Hibernate: create table user (id bigint not null auto_increment, type integer not null, name varchar(255), salary decimal(19,2), primary key (id))
	 * Hibernate: insert into user (name, type) values (?, 1)
	 * Hibernate: insert into user (name, salary, type) values (?, ?, 2)
	 */
	@Test
	public void testInheritRelationSave() {
		com.mark.project.hibernateDemo.inherit_relation.domain.User u = new
				com.mark.project.hibernateDemo.inherit_relation.domain.User();
		u.setName("Mark");
		Customer customer = new Customer();
		customer.setName("Tom");
		customer.setSalary(new BigDecimal(1500));
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(u);
		session.save(customer);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 查询User用户表的SQL语句
	 * Hibernate: select user0_.id as id1_0_0_, user0_.name as name3_0_0_, user0_.salary as salary4_0_0_, user0_.type as type2_0_0_
	 * from user user0_ where user0_.id=?
	 * 查询Customer客户表的SQL语句
	 * Hibernate: select customer0_.id as id1_0_0_, customer0_.name as name3_0_0_, customer0_.salary as salary4_0_0_
	 * from user customer0_ where customer0_.id=? and customer0_.type=2
	 * 从两天SQL语句可以看出，区别在于查询Customer客户表的时候会加上对应的type值。
	 */
	@Test
	public void testInheritRelationGet() {
		Session session = HibernateUtil.getSession();
		com.mark.project.hibernateDemo.inherit_relation.domain.User user =
				(com.mark.project.hibernateDemo.inherit_relation.domain.User)
						session.get
								(com.mark.project.hibernateDemo.inherit_relation.domain.User.class,
										1L);
		System.out.println(user);
		Customer customer = (Customer) session.get(Customer.class, 2L);
		System.out.println(customer);

	}


	/**
	 * 查询名字带有a的员工数据
	 */
	@Test
	public void testQueryBySql() {
		String sql = "SELECT * FROM t_employee AS e WHERE e.name LIKE ? AND e.id BETWEEN ? AND ?";
		Session session = HibernateUtil.getSession();
		//创建一个SQLQuery对象
		SQLQuery query = session.createSQLQuery(sql);
		//查询得到的是一个Object数组 设置占位符的值
		List<Object[]> list = query.setString(0, "%a%").setLong(1, 1).setLong(2, 10).list();
		for (Object[] item : list) {
			System.out.println(Arrays.toString(item));
		}
		session.close();
	}

	/**
	 * 查询名字带有a的员工数据
	 * <p>
	 * 从面向对象的角度去看.要用实体类对象作为表并且给它取个别名
	 */
	@Test
	public void testQueryByHql() {
		String sql = "SELECT e FROM Employee e WHERE e.name LIKE ? AND e.id BETWEEN ? AND ?";
		Session session = HibernateUtil.getSession();
		//创建一个SQLQuery对象
		Query query = session.createQuery(sql);
		//查询得到的是一个Object数组 设置占位符的值
		List<com.mark.project.hibernateDemo.query.domain.Employee> list = query.setString(0, "%a%").setLong(1, 1).setLong(2, 10).list();
		for (com.mark.project.hibernateDemo.query.domain.Employee item : list) {
			System.out.println(item);
		}
		session.close();
	}


	/**
	 * 查询数据通过Criteria来查询
	 */
	@Test
	public void testQueryByCriteria() {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(com.mark.project.hibernateDemo.query.domain.Employee.class);
		criteria.add(Restrictions.like("name", "a", MatchMode.ANYWHERE)) //添加条件
				.add(Restrictions.between("id", 1L, 10L));
		List<com.mark.project.hibernateDemo.query.domain.Employee> employees = criteria.list(); //真正执行查询的地方
		for (com.mark.project.hibernateDemo.query.domain.Employee item : employees) {
			System.out.println(item);
		}
		session.close();
	}

	/**
	 * 查询总条数
	 * Hibernate: select count(employee0_.id) as col_0_0_ from t_employee employee0_
	 */
	@Test
	public void testQueryCount() {
		//使用HQL来查询总数
		String hql = "SELECT COUNT(e) FROM Employee e";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		Long count = (Long) query.uniqueResult();//查询总条数
		session.close();
		System.out.println("count = " + count);
	}

	/**
	 * 分页查询
	 * Hibernate: select employee0_.id as id1_2_, employee0_.name as name2_2_, employee0_.salary as salary3_2_,
	 * employee0_.hireDate as hireDate4_2_, employee0_.dept_id as dept_id5_2_ from t_employee employee0_ limit ?, ?
	 */
	@Test
	public void testQueryPage() {
		int currentPage = 2;
		int pageSize = 5;
		//查询分页
		String hql = "SELECT e FROM Employee e";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		//setFirstResult表示从哪一个位置开始
		//setMaxResults 表示最大的数据数量
		List<com.mark.project.hibernateDemo.query.domain.Employee> employees =
				query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
		session.close();
		for (com.mark.project.hibernateDemo.query.domain.Employee item : employees) {
			System.out.println(item);
		}

	}

	/**
	 * 使用位置占位符来设置参数
	 * 位置从0开始
	 */
	@Test
	public void testQueryByIndex() {
		String hql = "SELECT e FROM Employee e WHERE e.salary >= ?";
		Session session = HibernateUtil.getSession();
		List<com.mark.project.hibernateDemo.query.domain.Employee> employees =
				session.createQuery(hql).setBigDecimal(0, new BigDecimal("7500")).list();
		session.close();
		for (com.mark.project.hibernateDemo.query.domain.Employee item : employees) {
			System.out.println(item);
		}

	}

	/**
	 * 使用名称占位符来设置参数
	 * 用法: 冒号+名称 ---> :salary
	 * 设置的时候就直接是使用salary这个名称
	 * 优点:
	 * 1. 和位置无关.不需要考虑位置的关系
	 * 2. 如果是设置相同的参数只要设置同一个名称来作为占位符就可以
	 * 例如: 查询名字和编号都带有a的员工
	 * SELECT e FROM Employee e WHERE e.name LIKE :key AND e.sn LIKE :key
	 * 3. 可以使用名称参数为in的参数设置多个值
	 */
	@Test
	public void testQueryByName() {
		String hql = "SELECT e FROM Employee e WHERE e.salary >= :salary";
		Session session = HibernateUtil.getSession();
		List<com.mark.project.hibernateDemo.query.domain.Employee> employees =
				session.createQuery(hql).setBigDecimal("salary", new BigDecimal("7500")).list();
		session.close();
		for (com.mark.project.hibernateDemo.query.domain.Employee item : employees) {
			System.out.println(item);
		}
	}

	/**
	 * 将查询的结果集数据每一行封装成List<Object>
	 * [2, Tom, 5000.00, 1, 销售部]
	 * [6, Ann, 5500.00, 2, 开发部]
	 * [1, Mark, 20000.00, 3, 人力资源部]
	 * [4, Lucy, 65478.00, 4, 财务部]
	 * [3, Tim, 100000.00, 5, 总监]
	 * [7, Bab, 7500.00, 5, 总监]
	 */
	@Test
	public void testQueryList() {
		String hql = "SELECT NEW LIST(e.id, e.name, e.salary, e.dept.id, e.dept.name) FROM Employee e";
		Session session = HibernateUtil.getSession();
		List<List<Object>> list = session.createQuery(hql).list();
		session.close();
		for (List<Object> objs : list) {
			System.out.println(objs);
		}
	}

	/**
	 * 将结果集数据每一行封装成map对象.key是查询的属性的位置,value是查询属性的值
	 * {0=2, 1=Tom, 2=5000.00, 3=1, 4=销售部}
	 * {0=6, 1=Ann, 2=5500.00, 3=2, 4=开发部}
	 * {0=1, 1=Mark, 2=20000.00, 3=3, 4=人力资源部}
	 * {0=4, 1=Lucy, 2=65478.00, 3=4, 4=财务部}
	 * {0=3, 1=Tim, 2=100000.00, 3=5, 4=总监}
	 * {0=7, 1=Bab, 2=7500.00, 3=5, 4=总监}
	 */
	@Test
	public void testQueryMap() {
		String hql = "SELECT NEW MAP(e.id, e.name, e.salary, e.dept.id, e.dept.name) FROM Employee e";
		Session session = HibernateUtil.getSession();
		List<Map<Integer, Object>> list = session.createQuery(hql).list();
		session.close();
		for (Map<Integer, Object> map : list) {
			System.out.println(map);
		}
	}

	/**
	 * 使用Map<String, Object>来接收.给查询的属性增加别名
	 * {deptId=1, eid=2, deptName=销售部, ename=Tom, salary=5000.00}
	 * {deptId=2, eid=6, deptName=开发部, ename=Ann, salary=5500.00}
	 * {deptId=3, eid=1, deptName=人力资源部, ename=Mark, salary=20000.00}
	 * {deptId=4, eid=4, deptName=财务部, ename=Lucy, salary=65478.00}
	 * {deptId=5, eid=3, deptName=总监, ename=Tim, salary=100000.00}
	 * {deptId=5, eid=7, deptName=总监, ename=Bab, salary=7500.00}
	 */
	@Test
	public void testQueryMapString() {
		String hql = "SELECT NEW MAP(e.id as eid, e.name as ename, e.salary as salary, e.dept.id as deptId, " +
				"e.dept.name as deptName) FROM Employee e";
		Session session = HibernateUtil.getSession();
		List<Map<String, Object>> list = session.createQuery(hql).list();
		session.close();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	/**
	 * 使用VO对象来封装查询的结果集数据
	 * 也就是根据查询出来的属性对象,对应的创建一个实体类.并且要提供构造器
	 * EmployeeVO{id=2, name='Tom', salary=5000.00, deptId=1, deptName='销售部'}
	 * EmployeeVO{id=6, name='Ann', salary=5500.00, deptId=2, deptName='开发部'}
	 * EmployeeVO{id=1, name='Mark', salary=20000.00, deptId=3, deptName='人力资源部'}
	 * EmployeeVO{id=4, name='Lucy', salary=65478.00, deptId=4, deptName='财务部'}
	 * EmployeeVO{id=3, name='Tim', salary=100000.00, deptId=5, deptName='总监'}
	 * EmployeeVO{id=7, name='Bab', salary=7500.00, deptId=5, deptName='总监'}
	 * 在配置文件中可以手动导入包,修改实体类的别名.查询的hql语句就可以不用写全限定名称
	 * <import class="EmployeeVO" rename="EmployeeVO"/>
	 */
	@Test
	public void testQueryVO() {
		String hql = "SELECT NEW com.mark.project.hibernateDemo.query.domain.EmployeeVO(e.id, e.name, e.salary, e.dept.id, e.dept.name) FROM Employee e";
		Session session = HibernateUtil.getSession();
		List<EmployeeVO> employeeVOs = session.createQuery(hql).list();
		session.close();
		for (EmployeeVO item : employeeVOs) {
			System.out.println(item);
		}
	}


	//	1，查询所有员工【查询实体类型】
	@Test
	public void test1() {
		Session session = HibernateUtil.getSession();
		//hql
		//List<com.mark.project.hibernateDemo.query.domain.Employee> employees = session.createQuery("SELECT e FROM Employee e").list();
		//sql 用sql查询出来的结果集是Object[]类型 所以要重新封装到实体类去
		List<Object[]> employees = session.createSQLQuery("SELECT * FROM t_employee").list();
		session.close();
		for ( Object[] e : employees ) {
			System.out.println(Arrays.toString(e));
		}
	}

	/**
	 * 2，查询所有员工的姓名和所属部门名称【查询特定属性】
		 [Mark, 销售部]
		 [Tom, 开发部]
		 [Tim, 人力资源部]
		 [Lucy, 财务部]
		 [Jason, 总监]
	 */
	@Test
	public void test2() {
		Session session = HibernateUtil.getSession();

		//hql 只能用Object[]来接收
		//List<Object[]> list = session.createQuery("SELECT e.name, e.dept.name FROM Employee e").list();

		//sql
		List<Object[]> list1 =
				session.createSQLQuery("SELECT e.name AS ename, d.name AS dname FROM t_employee AS e, t_dept AS d WHERE e.id = d.id").list();

		session.close();
		for (Object[] arr : list1 ) {
			System.out.println(Arrays.toString(arr));
		}
	}
	//	3，查询出所有在深圳和广州工作的员工【查询结果过滤】

	/**
	 * Hibernate: select employee0_.id as id1_2_, employee0_.name as name2_2_, employee0_.salary as salary3_2_,
	 * employee0_.hireDate as hireDate4_2_, employee0_.dept_id as dept_id5_2_
	 * from t_employee employee0_ cross join t_dept department1_ where employee0_.dept_id=department1_.id and (department1_.city=? or department1_.city=?)
	 */
	@Test
	public void test3() {
		Session session = HibernateUtil.getSession();

		//hql
		/*List<com.mark.project.hibernateDemo.query.domain.Employee> employees =
				session.createQuery("SELECT e FROM Employee e WHERE e.dept.city = ? OR e.dept.city = ?")
				.setParameter(0, "深圳").setParameter(1, "广州").list();
		for (com.mark.project.hibernateDemo.query.domain.Employee e : employees ) {
			System.out.println(e);
		}*/

		//sql
		//
		List<Object[]> list =
				session.createSQLQuery("SELECT e.id AS eid, e.name, e.id, e.salary, e.hireDate FROM t_employee AS e, t_dept AS d WHERE e.dept_id = d.id AND (d.city = ? OR d.city = ?)")
				.setParameter(0, "深圳").setParameter(1, "广州").list();
		for ( Object[] arr : list ) {
			System.out.println(Arrays.toString(arr));
		}
		session.close();
	}


	//	4，查询出所有员工信息，按照月薪排序【查询排序】
	@Test
	public void test4() {
		Session session = HibernateUtil.getSession();

		//hql
		List<com.mark.project.hibernateDemo.query.domain.Employee> employees =
				session.createQuery("SELECT e FROM Employee e ORDER BY e.salary").list();
		session.close();
		for (com.mark.project.hibernateDemo.query.domain.Employee e : employees ) {
			System.out.println(e);
		}
	}
	//	5，查询出所有员工信息，按照部门编号排序【使用关联对象属性排序】
	@Test
	public void test5() {
		Session session = HibernateUtil.getSession();
		//hql
		List<com.mark.project.hibernateDemo.query.domain.Employee> employees =
				session.createQuery("SELECT e FROM Employee e ORDER BY e.dept.id").list();
		session.close();
		for ( com.mark.project.hibernateDemo.query.domain.Employee e : employees ) {
			System.out.println(e);
		}
	}
	//	6，查询出在恩宁路和八宝街上班的员工信息【使用IN】
	//	7，查询出工资在5000-6000的员工【使用BETWEEN..AND..】
	//	8，查询出姓名包含er或en的员工【使用LIKE】
	//	9，查询出有员工的部门【使用DISTINCT】


}

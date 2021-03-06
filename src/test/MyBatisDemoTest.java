import com.mark.domain.User;
import com.mark.project.MyBatisDemo.domain.*;
import com.mark.project.MyBatisDemo.mapper.*;
import com.mark.project.MyBatisDemo.page.PageResult;
import com.mark.project.MyBatisDemo.query.QueryObject;
import com.mark.project.MyBatisDemo.service.impl.AccountServiceImpl;
import com.mark.project.springDemo.day03.jdbc.domain.Emp;
import com.mark.project.springDemo.day03.tx.service.IAccountService;
import com.mark.project.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mark on 17/8/20.
 */
public class MyBatisDemoTest {

    @Test
    public void save() throws IOException {
        Account account = new Account();
        account.setName("Mark");
        account.setAge(20);
        //创建一个SqlSessionFactory对象
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/mybatis.cfg.xml"));
        //创建一个SqlSession对象
        SqlSession session = sf.openSession();
        session.insert("com.mark.project.MyBatisDemo.domain.save", account);
        //提交事务
        session.commit();
        //关闭session
        session.close();
    }

    @Test
    public void update() throws Exception {
        Account account = new Account();
        account.setAge(18);
        account.setName("Tom");
        account.setId(1L);
        SqlSession session = MyBatisUtil.getSession();
        session.update("com.mark.project.MyBatisDemo.domain.update", account);
        session.commit();
        session.close();
    }

    @Test
    public void delete() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        session.delete("com.mark.project.MyBatisDemo.domain.delete", 1L);
        session.commit();
        session.close();
    }

    @Test
    public void get() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        Account account = session.selectOne("com.mark.project.MyBatisDemo.domain.get", 2L);
        System.out.println(account);
        session.close();
    }

    @Test
    public void list() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        List<Account> accounts = session.selectList("com.mark.project.MyBatisDemo.domain.list");
        for (Account account : accounts ) {
            System.out.println(account);
        }
        session.close();
    }

    @Test
    public void save2() throws Exception {
        Account account = new Account();
        account.setName("Lucy");
        account.setAge(19);
        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.save(account);
        session.commit(); //记得提交
        session.close();
    }


    @Test
    public void get2() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Account account = userMapper.get(3L);
        System.out.println(account);
        session.close();
    }

    @Test
    public void queryByCondition() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        QueryObject qo = new QueryObject();
        qo.setKeyword("M");
        List<Account> accounts = userMapper.queryByCondition(qo);
        for( Account account : accounts ) {
            System.out.println(account);
        }
    }

    @Test
    public void updateByCondition() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Account account = new Account();
        account.setId(3L);
        account.setName("K");
        account.setAge(33);
        mapper.updateByCondition(account);
        session.commit();
        session.close();
    }


    @Test
    public void queryForIn() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<Long> ids = new ArrayList<Long>();
        ids.add(3L);
        ids.add(11L);
        ids.add(5L);
        List<Account> accounts = mapper.queryForIn(ids);
        for ( Account account : accounts ) {
            System.out.println(account);
        }
        session.close();
    }

    @Test
    public void testQueryPage() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        com.mark.project.MyBatisDemo.service.IAccountService accountService = new AccountServiceImpl();
        QueryObject qo = new QueryObject();
        qo.setKeyword("M");
        qo.setCurrentPage(1);
        qo.setPageSize(2);
        PageResult<Account> result = accountService.pageQuery(qo);
        List<Account> data = result.getData();
        for ( Account account : data ) {
            System.out.println(account);
        }
    }

    @Test
    public void testLogin() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Account account = userMapper.login("Mark", "12345");
        System.out.println(account);
        session.close();
    }


    /**
     *
     * 将我们需要传递的参数封装到map中。
     * 在对应的xml文件里就会自动的根据我们所定义的名称去map中寻找值。
     *
     * @throws Exception
     */
    @Test
    public void testLoginMap() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "Mark");
        map.put("password", "12345");
        mapper.loginByMap(map);
        session.close();
    }


    /**
     * Many2One的关系
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);

        Department department = new Department();
        department.setName("开发部");
        Employee employee = new Employee();
        employee.setName("Mark");
        employee.setDept(department);

        departmentMapper.save(department);
        employeeMapper.save(employee);
        session.commit();
        session.close();
    }


    @Test
    public void testGet() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Employee em = employeeMapper.get(1L);
        System.out.println(em);
        System.out.println(em.getDept());
        session.close();
    }

    @Test
    public void testSelect() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employees = employeeMapper.select();
        for(Employee employee : employees ) {
            System.out.println(employee);
            System.out.println(employee.getDept());
        }
        session.close();
    }

    /**
     *
     * Student:Teacher = 1:多
     * 由Student作为One方去管理
     *
     * 数据库：Teacher表持有Student的外键.但是我们的侧重点是one方，所以我们让one方去更新外键，而不是直接在many方更新外键
     *
     * Mapper文件：我们需要在One方的Mapper中添加更新Many方外键的SQL语句
     *
     * @throws Exception
     */
    @Test
    public void testOne2ManySave() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class); //One
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);

        Teacher teacher = new Teacher();
        teacher.setName("Jason");
        Teacher teacher1 = new Teacher();
        teacher.setName("Jason1");
        Teacher teacher2 = new Teacher();
        teacher.setName("Jason2");
        Student student = new Student();
        student.setName("Mark");
        student.getTeachers().add(teacher);
        student.getTeachers().add(teacher1);
        student.getTeachers().add(teacher2);

        studentMapper.save(student);
        teacherMapper.save(teacher);
        //one方做完新增后要对外键进行更新
        for ( Teacher teach : student.getTeachers() ) {
            studentMapper.updateForStuId(student.getId(), teach.getId());
        }

        session.commit();
        session.close();
    }


    @Test
    public void testOne2ManySelect() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.select();
        for(Student stu : students ) {
            List<Teacher> teachers = stu.getTeachers();
            System.out.println(stu);
            for ( Teacher teacher : teachers ) {
                System.out.println(teacher);
            }
        }
        session.close();
    }

    @Test
    public void testOne2ManyGet() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student student = studentMapper.get(1L);
        System.out.println(student);
        List<Teacher> teachers = student.getTeachers();
        for(Teacher teacher : teachers ) {
            System.out.println(teacher);
        }
        session.close();
    }

    @Test
    public void testDelete() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        //先更新掉many方的外键约束
        studentMapper.updateRelation(1L);
        //在删除one方的数据
        studentMapper.delete(1L);
        session.commit();
        session.close();
    }


    @Test
    public void testMany2ManySave() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        ProductMapper productMapper = session.getMapper(ProductMapper.class);
        SupplierMapper supplierMapper = session.getMapper(SupplierMapper.class);

        //创建对象
        Product product1 = new Product();
        product1.setName("iPhone8");
        Product product2 = new Product();
        product2.setName("三星");

        Supplier supplier1 = new Supplier();
        supplier1.setName("苹果");
        Supplier supplier2 = new Supplier();
        supplier2.setName("富士康");
        supplier1.getProducts().add(product1);
        supplier1.getProducts().add(product2);
        supplier2.getProducts().add(product1);
        supplier2.getProducts().add(product2);

        productMapper.save(product1);
        productMapper.save(product2);
        supplierMapper.save(supplier1);
        supplierMapper.save(supplier2);
        for (Product product : supplier1.getProducts()) {
            supplierMapper.saveMiddleData(product.getId(), supplier1.getId());
        }
        for ( Product product : supplier2.getProducts() ) {
            supplierMapper.saveMiddleData(product.getId(), supplier2.getId());
        }
        session.commit();
        session.close();

    }


    @Test
    public void select() throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        SupplierMapper mapper = session.getMapper(SupplierMapper.class);
        Supplier supplier = mapper.select(3L);
        System.out.println(supplier);
        for( Product product : supplier.getProducts() ) {
            System.out.println(product);
        }
        session.close();
    }


}

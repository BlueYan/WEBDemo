import com.mark.project.MyBatisDemo.domain.Account;
import com.mark.project.MyBatisDemo.mapper.UserMapper;
import com.mark.project.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.util.List;

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

}

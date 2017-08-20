import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Mark on 17/8/20.
 */
public class MyBatisDemoTest {

    @Test
    public void save() throws IOException {
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybaits.cfg.xml"));
    }

}

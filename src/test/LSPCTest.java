import com.mark.project.login_shopcart_checkcode.dao.IUserDao;
import com.mark.project.login_shopcart_checkcode.dao.impl.UserDaoImpl;
import org.junit.Test;
import com.mark.project.login_shopcart_checkcode.domain.User;
/**
 * Created by Administrator on 2017/6/24.
 */
public class LSPCTest {

	private IUserDao userDao = new UserDaoImpl();

	@Test
	public void testLogin() {
		String username = "admin";
		String password = "admin";
		User user = userDao.login(username, password);
		System.out.println(user);
	}

}

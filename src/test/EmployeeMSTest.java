import com.mark.project.employeeMS.dao.IEmployeeDao;
import com.mark.project.employeeMS.dao.impl.EmployeeDaoImpl;
import com.mark.project.employeeMS.domain.Employee;
import com.mark.project.employeeMS.page.PageResult;
import com.mark.project.employeeMS.query.EmployeeQuery;
import org.junit.Test;

import java.awt.font.FontRenderContext;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public class EmployeeMSTest {

	private IEmployeeDao employeeDao = new EmployeeDaoImpl();

	@Test
	public void testSave() {
		for ( int i = 0; i < 100; i++ ) {
			Employee e = new Employee(null, "emp" + (i + 1), "123456", "emp" + i + 1 + "@123.com",
					new Date());
			employeeDao.save(e);
		}
	}



	@Test
	public void testDelete() {
		employeeDao.delete(1L);
	}


	@Test
	public void testUpdate() {
		Employee e = new Employee(2L, "Mark", "123456", "mark_yan@123.com", new Date());
		employeeDao.update(e);
	}



	@Test
	public void testGet() {
		Employee e = employeeDao.get(2L);
		System.out.println(e);
	}


	@Test
	public void testList() {
		List<Employee> employees = employeeDao.list();
		for ( Employee item  : employees ) {
			System.out.println(item);
		}
	}

	@Test
	public void testQuery() {
		EmployeeQuery query = new EmployeeQuery();
		query.setHigherDate("2017-07-01");
		List<Employee> employees = employeeDao.query(query);
		for ( Employee employee : employees ) {
			System.out.println(employee);
		}
	}

	@Test
	public void testPageQuery() {
		EmployeeQuery query = new EmployeeQuery();
		query.setHigherDate("2017-07-01");
		query.setCurrentPage(1);
		query.setPageSize(5);
		PageResult<Employee> pageResult = employeeDao.pageQuery(query);
		System.out.println(pageResult);
	}

}

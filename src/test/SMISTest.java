import com.mark.project.smis.dao.IStudentDao;
import com.mark.project.smis.dao.impl.StudentDaoImpl;
import com.mark.project.smis.domain.Student;
import com.mark.project.smis.page.PageResult;
import com.mark.project.smis.query.StudentQueryObject;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */
public class SMISTest {

	private IStudentDao stuDao = new StudentDaoImpl();

	@Test
	public void testStudentList() {
		List<Student> stuList = stuDao.list();
		for( Student stu : stuList ) {
			System.out.println(stu);
		}
	}

	@Test
	public void deleteStudent() {
		stuDao.delete(2);
	}

	@Test
	public void addStudent(){
		stuDao.add(new Student(0, "Tim", 22));
	}

	@Test
	public void getStudent() {
		Student stu = stuDao.get(5);
		System.out.println(stu);
	}

	@Test
	public void updateStudent() {
		Student student = new Student(5, "Lisa", 21);
		stuDao.update(student);
	}

	@Test
	public void queryStudent() {
		List<Student> list = stuDao.query("a", null, null);
		System.out.println(list);
	}

	@Test
	public void testPage() {
		Integer currentPage = 1;
		Integer pageSize = 10;
		PageResult<Student> stuPageResult = stuDao.page(currentPage, pageSize);
		System.out.println(stuPageResult);
	}

	@Test
	public void testPageQuery() {
		StudentQueryObject stuqo = new StudentQueryObject();
		stuqo.setName("M");
		stuqo.setCurrentPage(1);
		stuqo.setPageSize(10);
		PageResult<Student> stuResult = stuDao.pageQuery(stuqo);
		System.out.println(stuResult);
	}
}

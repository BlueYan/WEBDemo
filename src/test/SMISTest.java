import com.mark.project.smis.dao.IStudentDao;
import com.mark.project.smis.dao.impl.StudentDaoImpl;
import com.mark.project.smis.domain.Student;
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

}

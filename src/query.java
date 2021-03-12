import java.util.ArrayList;
import java.util.List;

import com.zsw.entity.Student;
import com.zsw.service.StudentService;
import com.zsw.service.impl.StudentServiceImpl;

public class query {

	public static void main(String[] args) {
		//StudentService service=new StudentServiceImpl();
		Student stu=new Student();
		stu.setDomitory_Name(null);
		System.out.println(stu.getDomitory_Name());
		
	}

}

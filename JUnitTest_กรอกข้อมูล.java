import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JUnitTest_กรอกข้อมูล {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNumAllStudentsNull() {
		int n = ProjectFrame.numAllStudentsNull("");
		//int n = ProjectFrame.numAllStudentsNull("100");
		assertEquals(1,n);
	}

	@Test
	public void testNumAllStudentsIsNumber() {
		// int n = ProjectFrame.numAllStudentsIsNumber("_jjo");
		int n = ProjectFrame.numAllStudentsIsNumber("100");
		assertEquals(1,n);
	}

	@Test
	public void testNameSubjectNull() {
		// int n = ProjectFrame.nameSubjectNull("");
		int n = PageLogIn.nameSubjectNull("CS284");
		assertEquals(1,n);
	}
	@Test
	public void testNumComeStudentsIsNumber() {
		int n = ProjectFrame.numComeStudentsIsNumber("50");
		assertEquals(1,n);
	}

	@Test
	public void testNumLateStudentsIsNumber() {
		int n = ProjectFrame.numLateStudentsIsNumber("50");
		assertEquals(1,n);
	}

	@Test
	public void testNumNotComeStudentsIsNumber() {
		int n = ProjectFrame.numNotComeStudentsIsNumber("50");
		assertEquals(1,n);
	}

}

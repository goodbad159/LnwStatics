import static org.junit.Assert.*;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JUnitTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testConnectToDatabase() {
		int n = ProjectFrame.connectToDatabase();
		assertEquals(1,n);
	}

	@Test
	public void testInsertToDatabase() {
		int n = ProjectFrame.insertToDatabase("CS284", 120, 100, 10, 10,"asd");
		assertEquals(1,n);
	}

	@Test
	public void testSelectInDatabase() {
		try {
			int[] n = ProjectFrame.selectInDatabase("CS284","asd");
		} catch (Exception e) {
			fail();
		}
	}


}

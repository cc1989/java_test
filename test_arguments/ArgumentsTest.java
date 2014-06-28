package test_arguments;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArgumentsTest {

	private static Arguments arguments = new Arguments();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTest() {
		int[] temp = arguments.test(new int[]{1, 2});
		assertEquals(temp[0], 2);
	}

}

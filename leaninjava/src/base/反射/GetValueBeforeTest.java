package 反射;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetValueBeforeTest {

	@Test
	public void testMain() {
		int i = 9;
		i = 4;
		System.out.println(i);
	}

	@Test
	public void testGetBefore() {
		fail("Not yet implemented");
	}

}

package xml;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DomDemoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryXml() {
		//读取
        DomDemo.queryXml();      
	}

	@Test
	public void testInsertXml() {
		//插入
        DomDemo.insertXml();
	}

}

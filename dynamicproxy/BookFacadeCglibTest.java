/**
 * Copyright © 2014Taobao.com 淘宝(中国)软件有限公司. All rights reserved.
 * Vsearch Support
 */
package dynamicproxy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookFacadeCglibTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		 BookFacadeCglib cglib=new BookFacadeCglib();  
	     BookFacadeImpl1 bookCglib=(BookFacadeImpl1)cglib.getInstance(new BookFacadeImpl1());  
	     bookCglib.addBook();  
	}

}

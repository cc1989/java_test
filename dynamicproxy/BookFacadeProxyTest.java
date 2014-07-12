/**
 * Copyright © 2014Taobao.com 淘宝(中国)软件有限公司. All rights reserved.
 * Vsearch Support
 */
package dynamicproxy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BookFacadeProxyTest {

	@Before
	public void setUp() throws Exception {
	}
	/**
	 * 
	 * @Methods: test 
	 * @Description: JDK的动态代理依靠接口实现，如果有些类并没有实现接口，则不能使用JDK代理，这就要使用cglib动态代理了
	 * @return void
	 */
	@Test
	public void test() {
		BookFacadeProxy proxy = new BookFacadeProxy();  //通用代理
		BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookProxy.addBook();
	}

}

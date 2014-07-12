/**
 * Copyright © 2014Taobao.com 淘宝(中国)软件有限公司. All rights reserved.
 * Vsearch Support
 */
package dynamicproxy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CountProxyTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		CountImpl countImpl = new CountImpl();
		CountProxy countProxy = new CountProxy(countImpl);
		countProxy.updateCount();
		countProxy.queryCount();
	}

}

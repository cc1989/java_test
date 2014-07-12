/**
 * Copyright © 2014Taobao.com 淘宝(中国)软件有限公司. All rights reserved.
 * Vsearch Support
 */
package dynamicproxy;

/**
 * 
 * @ClassName: CountImpl 
 * @Description: 委托类(包含业务逻辑)
 * @author huangchen.hc@alibaba-inc.com
 * @date 2014年7月11日 下午5:34:03 
 *
 */
public class CountImpl implements Count {  
	  
    @Override  
    public void queryCount() {  
        System.out.println("查看账户方法...");  
  
    }  
  
    @Override  
    public void updateCount() {  
        System.out.println("修改账户方法...");  
  
    }  
  
}  
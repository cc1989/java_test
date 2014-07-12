/**
 * Copyright © 2014Taobao.com 淘宝(中国)软件有限公司. All rights reserved.
 * Vsearch Support
 */
package dynamicproxy;

/**
 * 
 * @ClassName: CountProxy 
 * @Description: 这是一个代理类（增强CountImpl实现类）
 * @author huangchen.hc@alibaba-inc.com
 * @date 2014年7月11日 下午5:35:07 
 *
 */
public class CountProxy implements Count {  
    private CountImpl countImpl;  
  
    /** 
     * 覆盖默认构造器 
     *  
     * @param countImpl 
     */  
    public CountProxy(CountImpl countImpl) {  
        this.countImpl = countImpl;  
    }  
  
    @Override  
    public void queryCount() {  
        System.out.println("事务处理之前");  
        // 调用委托类的方法;  
        countImpl.queryCount();  
        System.out.println("事务处理之后");  
    }  
  
    @Override  
    public void updateCount() {  
        System.out.println("事务处理之前");  
        // 调用委托类的方法;  
        countImpl.updateCount();  
        System.out.println("事务处理之后");  
  
    }  
  
}  

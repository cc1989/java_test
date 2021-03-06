package polymorphism;

import org.junit.Before;
import org.junit.Test;


public class ShapeTest {

	private  Shape shape1;
	private  Shape shape2;
	private  Shape shape3;
	
	@Before
	public void setUp() throws Exception {
		shape1 = new Shape();
		shape2 = new Circle();
		shape3 = new Square();
	}

	@Test
	public void testDraw() {
		shape1.draw();
		shape2.draw();
		shape3.draw();
		if (shape3 instanceof Square)  //既可以是子类的实例，也可以是父类的实例
			System.out.println("ok");  
		System.out.println(shape3.getClass().getName());  //动态获取引用所指对象的类型
		//格式化输出
		System.out.printf("hashcode:%d %<#x %#x %2$#x\n", shape1.hashCode(), shape2.hashCode());
	}

	@Test
	public void testErase() {
		shape1.erase();
		shape2.erase();
		shape3.erase();
	}

}

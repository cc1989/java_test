package polymorphism;

public class Shape {
	Shape(){ System.out.println("Shape constructor"); } 
	public void draw(){ System.out.println("shape draw"); }
	public void erase(){ System.out.println("shape erase"); }
}

class Circle extends Shape{
	Circle(){ System.out.println("Circle constructor"); } 
	public void draw(){ System.out.println("circle draw"); }
	public void erase(){ System.out.println("circle erase"); }
}

class Square extends Shape{
	Square(){ System.out.println("Square constructor"); } 
	public void draw(){ System.out.println("square draw"); }
	public void erase(){ System.out.println("square erase"); }
}

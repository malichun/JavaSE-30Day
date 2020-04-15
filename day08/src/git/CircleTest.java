package git;
/*
 * 2.利用面向对象的编程方法，设计类Circle计算圆的面积。
 */
//测试类
public class CircleTest {

	public static void main(String[] args) {
		Circle c1 = new Circle();
		
		c1.radius = 2.1;
		
		double area = c1.findArea();
		System.out.println(area);
	}
}
//圆:3.14*r*r
class Circle{
	//属性
	double radius;
	
	//圆的面积方法
	public double findArea(){
		double area = 3.14 * radius * radius;
		return area;
	}	
}

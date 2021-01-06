package cse3040_hw2_20171702;

abstract class Shape{
	abstract double area();
}

class Circle extends Shape {
	private double number;
	public Circle(double n) {
		number=n;
	}
	public double area() {
		return Math.PI*number*number;
	}
}
class Square extends Shape {
	private double number;
	public Square(double n) {
		number=n;
	}
	public double area() {
		return number*number;
	}
}
class Rectangle extends Shape {
	private double number1;
	private double number2;
	public Rectangle(double m,double n) {
		number1=m;
		number2=n;
	}
	public double area() {
		return number1*number2;
	}
}
public class Problem08 {		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape[] arr = { new Circle(5.0), new Square(4.0), 
						new Rectangle(3.0, 4.0), new Square(5.0)};
		System.out.println("Total area of the shapes is: " + sumArea(arr));
	}
	public static double sumArea(Shape[] arr) {
		double result=0;
		for (int i=0;i<arr.length;i++) {
			result+=arr[i].area();
		}
		return result;
	}

}

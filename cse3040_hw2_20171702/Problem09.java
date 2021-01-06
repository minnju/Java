package cse3040_hw2_20171702;
class Point{
	private double darr[];
	Point(double[] arr) {
		darr=new double[arr.length];
		darr=arr;
	}
	public double Size() {
		return this.darr.length;
	}
	public double Data(int i) {
		return darr[i];
	}
}
class EuclideanDistance{
	public static double getDist(Point p1,Point p2) {
		double result=0;
		if (p1==null) {
			return -1;		
		}
		if (p2==null) {
			return -1;		
		}
		if(p1.Size()!=p2.Size()) {
			return -1;
		}
		for (int i=0;i<p1.Size();i++) {
			result+=Math.pow(p1.Data(i)-p2.Data(i), 2);
		}
		return Math.sqrt(result);
	}
}
class ManhattanDistance{
	public static double getDist(Point p1,Point p2) {
		double result=0;
		if (p1==null) {
			return -1;		
		}
		if (p2==null) {
			return -1;		
		}
		if(p1.Size()!=p2.Size()) {
			return -1;
		}
		for(int i=0;i<p1.Size();i++) {
			result+=Math.abs(p1.Data(i)-p2.Data(i));
		}
		return result;
	}
	
}
public class Problem09 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(new double[] {1.0, 2.0, 3.0});
		Point p2 = new Point(new double[] {4.0, 5.0, 6.0}); 
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p1, p2));
		System.out.println("Manhattan Distance: " + ManhattanDistance.getDist(p1, p2)); 
		Point p3 = new Point(new double[] {1.0, 2.0, 3.0}); 
		Point p4 = new Point(new double[] {4.0, 5.0});
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p3, p4));
		System.out.println("Manhattan Distance: " + ManhattanDistance.getDist(p3, p4));
	}

}

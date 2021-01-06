package cse3040_hw2_20171702;
class Points{
	private double darr[];
	Points(double[] arr) {
		darr=new double[arr.length];
		darr=arr;
	}
	public boolean equals(Points p2) {
		double result1=0;
		double result2=0;
		if(p2==null) {
			return false;
		}
		for(int i=0;i<p2.Size();i++) {
			result1+=p2.Data(i);
		}
		for (int i=0;i<darr.length;i++) {
			result2+=darr[i];
		}
		if(result1==result2) {
			return true;
		}
		return false;
	}	
	public double Size() {
		return this.darr.length;
	}
	public double Data(int i) {
		return darr[i];
	}
	public String toString() {
		double result=0;
		for(int i=0;i<darr.length;i++) {
			result+=darr[i];
		}
		return "[sum of points: "+result+"]";
	}
}


public class Problem10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Points p1 = new Points(new double[] {1.0, 2.0, 3.0});
		Points p2 = new Points(new double[] {4.0, 5.0, 6.0}); 
		System.out.println(p1); 
		System.out.println(p2);
		System.out.println(p1.equals(p2));
		Points p3 = new Points(new double[] {1.0, 4.0, 7.0});
		Points p4 = new Points(new double[] {3.0, 9.0}); 
		System.out.println(p3); 
		System.out.println(p4); 
		System.out.println(p3.equals(p4)); 
		Points p5 = new Points(new double[] {1.0, 2.0});
		Points p6 = null; 
		System.out.println(p5); 
		System.out.println(p6); 
		System.out.println(p5.equals(p6));

	}

}

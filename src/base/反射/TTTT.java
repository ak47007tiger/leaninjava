package 反射;

public class TTTT {

	public static void  a(){
		double r = b(1,4.4);
		System.out.println(r);
	}
	public static double  b(int i, double b){
		return i + b;
	}
	public static void main(String[] args) {
		a();
	}
}

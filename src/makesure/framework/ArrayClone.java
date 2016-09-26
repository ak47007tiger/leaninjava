package framework;

public class ArrayClone {

	public static void main(String[] args) {
		int[] a = new int[2];
		a[0] = 0;
		a[1] = 1;
		int[] b = a.clone();
		System.out.println(a.equals(b));
		System.out.println(a);
		System.out.println(b);
		System.out.println(a[0] + "," + a[1]);
		System.out.println(b[0] + "," + b[1]);
	}
}

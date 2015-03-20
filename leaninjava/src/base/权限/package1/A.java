package 权限.package1;

public class A {

	public void printA(int a){
		System.out.println(a);
	}
	protected void printB(double b){
		System.err.println(b);
	}
	@SuppressWarnings("unused")
	private void printC(long c){
		System.out.println(c);
	}
}

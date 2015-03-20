package 类接口继承转型;

public class PrintA extends Print{

	@Override
	public void print() {
		System.out.println("print in PrintA");
	}
	public void printA(){
		System.out.println("printA in PrintA");
	}
	public static void main(String[] args) {
		Print print = new PrintA();
		print.print();
	}
}

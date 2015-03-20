package 类接口继承转型;

public class PrintB extends PrintA{

	public void print(){
		System.out.println("print in printB");
	}
	public void printB(){
		System.out.println("printB in printB");
	}
	public static void main(String[] args) {
	
		Print print = new PrintB();
		print.print$();
		print.print();
		PrintA printA = (PrintA)print;
		printA.print();//print in printB
		printA.printA();
		
		System.out.println("------------------");
		PrintA pa = new PrintA();
		PrintB pb = (PrintB)pa;
		pb.printA();
	}
	
}

package languebase;

public class ClassImpsMulSameFunc extends ClassWithSameFunc1 implements InfWithSameFunc1,InfWithSameFunc2{
	@Override
	public void test1() {
		System.out.println("OK");
	}
	public static void main(String[] args) {
		
	}
}

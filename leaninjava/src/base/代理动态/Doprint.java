package 代理动态;

public class Doprint implements IDoprint{

	@Override
	public void print(String tag) {
		System.out.println("tag in print : " + tag);
		System.out.println("print in doprint");
	}

	@Override
	public void printA(String tag) {
		System.out.println("tag in print : " + tag);
		System.out.println("printA in doprint");
	}

}

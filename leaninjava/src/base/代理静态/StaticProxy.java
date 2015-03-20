package 代理静态;

public class StaticProxy implements IPrint{

	
	//显然，这种代理没有什么意义。。。
	IPrint print = new Print();
	@Override
	public void print() {
		System.out.println("print in static Proxy");
		print.print();
	}

	
}

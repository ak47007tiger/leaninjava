package 代理动态;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DoprintProxy implements InvocationHandler {
	private IDoprint resource = null;

	public DoprintProxy() {
		resource = new Doprint();
	}

	// 这里还可以添加新的接口实现类

	public IDoAdd getDoAdd() {
		IDoAdd doAdd = null;
		doAdd = (IDoAdd) Proxy.newProxyInstance(IDoAdd.class.getClassLoader(),
				new Class[] { IDoAdd.class }, new DoAddHandler());
		return doAdd;
	}

	// 新的处理者，可以不实现一个接口，仅仅给一个方法的处理器
	class DoAddHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public IDoprint create() {
		IDoprint returnResource = null;
		// 代理签约
		returnResource = (IDoprint) Proxy.newProxyInstance(
				IDoprint.class.getClassLoader(),
				new Class[] { IDoprint.class }, this);
		return returnResource;
	}

	/**
	 * 所有的方法都要经过代理，那么方法执行的结果就可以和调用时的预测结果不同了 很强的灵活性 一个对象只有一个代理 一个代理可以代理多个对象
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object o = null;
		for (Object arg : args) {
			System.out.println("tag in proxy:" + arg);
		}
		// 可以使用实现类的方法，也可以单独实现
		o = method.invoke(resource, args);
		return o;
	}

	public static void main(String[] args) throws Throwable {
		System.out.println("___________________1");
		DoprintProxy doprintProxy = new DoprintProxy();
		IDoprint doprint1 = doprintProxy.create();
		doprint1.print("1");
		doprint1.printA("1");
		System.out.println("___________________2");
		IDoprint doprint2 = new Doprint();
		doprint2.print("2");
		doprint2.printA("2");
		System.out.println("_____________________3");
		IDoprint doprint3 = Doprint.class.newInstance();
		doprint3.print("3");
		doprint3.printA("3");
	}
}

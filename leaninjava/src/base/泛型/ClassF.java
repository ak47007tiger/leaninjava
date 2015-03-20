package 泛型;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.util.Stack;
import java.util.Vector;

public class ClassF<T> {

	public ClassF() {
	}
	@SuppressWarnings("unchecked")
	Vector<T> vector = (Vector<T>) new Vector<Object>();
	Vector<T> vector2 = new Vector<T>();
	void test1(T t,Class<T> cot) throws InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException{
		T t1 = null;
		t1 = cot.getDeclaredConstructor(new Class<?>[]{}).newInstance(new Object[]{});
		System.out.println(t1);
	}
	@SuppressWarnings("unchecked")
	void test2(T t,Class<T> cot) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Constructor<?>[] constructors = cot.getConstructors();
		for(Constructor<?> c : constructors){
			int auth = c.getModifiers();
			if(Member.PUBLIC != auth){
				c.setAccessible(true);
				//这里检查无参构造函数
				if(0 == c.getParameterTypes().length){
					t = (T) c.newInstance(new Object[]{});
				}else{
					t = null;
				}
			}
		}
	}
	T t;
	T get(){
		return t;
	}
//	static List<T> list1 = new LinkedList<T>(); //这样会报错
	static Stack<Object> stack1 = new Stack<Object>();
	public static void push(Object t){
		stack1.push(t);
	}
	public static Object pop(){
		return stack1.pop();
	}
}

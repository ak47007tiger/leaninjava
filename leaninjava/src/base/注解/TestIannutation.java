package 注解;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@ClassAnotation(name ="test",Results = {
		@Result(name = "test1"), 
		@Result(name = "test2") })
public class TestIannutation {

	public static void main(String[] args) {
		Annotation annotations[] = TestIannutation.class.getAnnotations();
		for(Annotation annotation : annotations){
			//可以通过造型，获得原来属性看控制权
			//这样可以得到注解所属的类别
			Class<?> cla = annotation.annotationType();
			if(cla.equals(ClassAnotation.class))
				System.out.println("find");
		}
		ClassAnotation ants = TestIannutation.class.getAnnotation(ClassAnotation.class);
		System.out.println("-1---------------");
		System.out.println("输出了注解的属性，其实是方法名");
		Method methods[] = ants.annotationType().getDeclaredMethods();
		for(Method m : methods){
			System.out.println(m.getName());
			//在这里还可以对method的注解进行解析和控制，总算让我找到了
		}
		System.out.println("0----------------");
		System.out.println("ants.annotationType():\t" + ants.annotationType());
		//接口是无法获得正确的类别的
		System.out.println("ants.getClass():\t" + ants.getClass());
		System.out.println("ClassAnotation.class:\t" + ClassAnotation.class);
		System.out.println(ants.name());
		System.out.println("1------------");
		Field fs1[] = ants.getClass().getDeclaredFields();
		//这输出的是什么我也不知道
		for(Field f : fs1){
			System.out.print(f.getName() + "\t");
		}
		System.out.println();
		System.out.println("2-------------");
		//什么也没有输出
		Field fs2[] = ClassAnotation.class.getDeclaredFields();
		for(Field f : fs2){
			System.out.println(f.getName());
		}
	}

}

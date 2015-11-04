package 注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassAnotation {
	
	/*
	 * 这是一个方法，返回和方法同名的变量的值
	 * 变量类型和方法返回值相同
	 * 注解的实现原理，jvm实现了该接口，根据注解的有效范围，保持实现类
	 */
	String name();
	Result [] Results();
	String namespace() default "/";
	int size() default 1024;
}

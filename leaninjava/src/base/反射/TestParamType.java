package 反射;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class TestParamType {

	public static void main(String[] args) {
		Method methods[] = User.class.getDeclaredMethods();
		for(Method m : methods){
			System.out.print(m.getName());
			Class<?>[] paraType = m.getParameterTypes();
			if(paraType.length == 0 ){
				System.out.println("\t has no param");
				continue;
			}else{
				System.out.println("\t has param");
			}
			for(Class<?> cla: paraType){
				System.out.println(cla);
			}
		}
		System.out.println("__________________________");
		for(Method m : methods){
			System.out.print(m.getName());
			if(m.getParameterTypes().length == 0){
				System.out.println("\t has no param");
				continue;
			}else{
				System.out.println("\t has param");
			}
			Type[] types =  m.getGenericParameterTypes();
			for(Type type : types){
				System.out.println(type.toString());
			}
		}
		System.out.println("--------------------------");
	}
}

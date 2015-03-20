package 反射;

import java.lang.reflect.Field;

public class TwoWArray {

	public void test(){
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException {
		@SuppressWarnings("unused")
		class Temp{
			private String[][]var;
			public String[][] getVar() {
				return var;
			}
			public void setVar(String[][] var) {
				this.var = var;
			}
			
		}
		Temp tempC = Temp.class.newInstance();
		String temp[][]={{"11","12","13"},{"21","22","23"},{"31","32","33"}};
		tempC.var = temp;
		Field var = Temp.class.getDeclaredField("var");
		String name = var.getName();
		System.out.println(name);
		Class<?> cla = var.getType();
		System.out.println(cla);
		String t[] = {};
		System.out.println(t.getClass());
		var.setAccessible(true);
		Object o = var.get(tempC);
		if(o instanceof String[][]){
			System.out.println("yes");
			String[][]k = (String[][])o;
			for(String key1[] : k){
				for(String key2: key1){
					System.out.println(key2);
				}
			}
		}
	}
		
}

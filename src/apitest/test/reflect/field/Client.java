package test.reflect.field;

import java.lang.reflect.Field;

public class Client {
	public static void main(String[] args) {
		Model model = new Model();
		try {
			Field interface1 = Model.class.getDeclaredField("interface1");
			interface1.setAccessible(true);
			System.out.println(interface1.getType());
			Object object = interface1.get(model);
			ClassA classA = (ClassA) object;
			System.out.println(classA.getName());
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

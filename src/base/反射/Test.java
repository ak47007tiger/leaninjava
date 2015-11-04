package 反射;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public class Test {

	public static void main(String[] args) throws Exception {
		String arr[] = {"a","b","c","d"};
		Class<? extends String[]> arrclass = arr.getClass();
		Field fs[]= arrclass.getDeclaredFields();
		System.out.println(fs.length);
		System.out.println(arrclass);
		Package packages[] = Package.getPackages();
		for(Package p : packages){
			System.out.println(p.getName());
		}
		System.out.println("--------------------------");
		System.out.println("验证class对象的field对象是否是单例的");
		AccessibleObject.setAccessible(User.class.getDeclaredFields(), true);
		Field fields[] = User.class.getDeclaredFields();
		User user = User.class.newInstance();
		user.setName("1");
		System.out.println(user.getName());
		for(Field f : fields){
			if("name".equals(f.getName())){
				f.setAccessible(true);
				f.set(user, "2");//报错了，权限仍然是private
			}
		}
		System.out.println(user.getName());
		System.out.println("---------------");
		System.out.println(User.class);
		System.out.println(user.getClass());
		System.out.println("实验，field不是单例，批量改权限--------------------");
		Field fields0[] = User.class.getDeclaredFields();
		AccessibleObject.setAccessible(fields0, true);
		for(Field f : fields0){
			if("name".equals(f.getName())){
				f.set(user, "3");//没有报错
				System.out.println(user.getName());
				break;
			}
		}
	}
	
}

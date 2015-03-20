package 反射;

import java.lang.reflect.Field;



public class GetValueBefore {

	public int p;
	public static void main(String[] args) throws Exception {
		GetValueBefore gvb = new GetValueBefore();
		System.out.println("___________________");
		Field f = GetValueBefore.class.getDeclaredField("p");
		gvb.p = 12;
		Object p = f.get(gvb);
		System.out.println(p.getClass());
		System.out.println(f.get(gvb));
		gvb.p = 10;
		
		System.out.println(f.get(gvb));
	}

	public void getBefore() {
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();
		for(StackTraceElement ste : sts){
			System.out.println(ste.getFileName());
		}
	}
}

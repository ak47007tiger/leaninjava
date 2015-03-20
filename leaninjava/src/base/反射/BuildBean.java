package 反射;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

/**
 * change map to bean
 * @author Administrator
 *
 */
public class BuildBean {

	/**
	 * 1.newInstence of T
	 * 2.each key to match field of bean
	 * 3.get setter of field
	 * 4.use setter to set value of field
	 * 5.return bean
	 * @return
	 */
	public <T> T build(HttpServletRequest req,Class<T> classOfBean) throws Exception{
		T t = null;
		t = classOfBean.newInstance();
		Field fields [] = classOfBean.getDeclaredFields();
		for(Field f : fields){
			String name = f.getName();
			Object value = req.getParameter(name);
			
			StringBuffer sb = new StringBuffer(name);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			Method setter = classOfBean.getDeclaredMethod("set" + sb.toString(), value.getClass());
			setter.setAccessible(true);
			setter.invoke(t, value);
		}
		return t;
	}
	/*
	Map<String,Object> params = new HashMap<String,Object>();
	params.put(name, value);
			Set<String> keys = params.keySet();
		Iterator<String> iterator = keys.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			StringBuffer nameOfsetter = new StringBuffer(key);
			nameOfsetter.setCharAt(0, Character.toUpperCase(nameOfsetter.charAt(0)));
			Object value = params.get(key);
			Method setter = classOfBean.getDeclaredMethod(nameOfsetter.toString(), value.getClass());
			setter.invoke(t, value);
		}*/
}

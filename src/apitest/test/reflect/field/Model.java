package test.reflect.field;

public class Model {

	private String str1;
	private final Interface1 interface1;
	public Model() {
		interface1 = new ClassA();
	}
}

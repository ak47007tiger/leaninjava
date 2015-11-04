package 分析post;

public class TestSocket {

	public static void main(String[] args) {
		char[] enter = {'\n'};
		System.out.println('\n' == enter[0]);
		System.out.println("enter" + new String(enter));
		System.out.println("a1");
		System.out.println("____________________");
		String lm = "--	-";
		String regex = "\\-{6}WebKitFormBoundary.+\\-{2}";
		System.out.println("____________________");
		System.out.println(lm.matches(regex));
	}
}

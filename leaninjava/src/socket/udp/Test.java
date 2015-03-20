package udp;

public class Test {

	public static void main(String[] args) {
		String regex = "[1-9]{1}[\\d]{0,2}[\\.]{1}[1-9]{1}[\\d]{0,2}[\\.]{1}[1-9]{1}[\\d]{0,2}[\\.]{1}[1-9]{1}[\\d]{0,2}";
		String ip = "0.0.32.1";
		System.out.println(ip.matches(regex));
		//192.168.1.113
	}
}

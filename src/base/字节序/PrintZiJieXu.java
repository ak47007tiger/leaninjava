package 字节序;

public class PrintZiJieXu {

	public static void main(String[] args) {
		int i = 0x1;
		System.out.println(i<<1);
		int ii = i << 4;
		System.out.printf("%x",ii - 1);
	}
}

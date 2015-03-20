package bitop;

public class Test {
	void testAnd(){
		
	}
	void testOr(){
		
	}
	void testYiHuo(){
		
	}
	void testQuFan(){
		IBitOption bitOption = new BitOptionImpl();
		int meight = -8;
		System.out.println(bitOption.getBitsValue(meight));
		System.out.println(bitOption.getBitsValue(~meight));
	}
	void testLeftMove(){
		IBitOption bitOption = new BitOptionImpl();
		int meight = -8;
		System.out.println(bitOption.getBitsValue(meight));
		System.out.println(bitOption.getBitsValue(meight << 2));
		System.out.println(bitOption.getBitsValue(meight));
		int eight = 8;
		System.out.println(bitOption.getBitsValue(eight));
		System.out.println(bitOption.getBitsValue(eight << 2));
		System.out.println(bitOption.getBitsValue(eight));
	}
	void testRightMove(){
		byte max = Byte.MAX_VALUE;
		byte min = Byte.MIN_VALUE;
		IBitOption bitOption = new BitOptionImpl();
		System.out.println(bitOption.getBitByPosition(8, max));
		System.out.println(bitOption.getBitsValue(max));
		System.out.println(bitOption.getBitByPosition(8, min));
		System.out.println(bitOption.getBitsValue(min));
		int meight = -8;
		System.out.println(bitOption.getBitsValue(meight));
		System.out.println(bitOption.getBitsValue(meight>>>2));//用0补，无符号
		System.out.println(bitOption.getBitsValue(meight>>2));//用1补，有符号
	}
	void testUnsignedRightMove(){
		
	}
	final static char[] digits = {
		'0' , '1' , '2' , '3' , '4' , '5' ,
		'6' , '7' , '8' , '9' , 'a' , 'b' ,
		'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
		'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
		'o' , 'p' , 'q' , 'r' , 's' , 't' ,
		'u' , 'v' , 'w' , 'x' , 'y' , 'z'
	    };
	String toUnsignedString(int i, int shift) {
		char[] buf = new char[32];
		int charPos = 32;
		int radix = 1 << shift;//2
		int mask = radix - 1;//1
		BitOptionImpl bitOptionImpl = new BitOptionImpl();
		do {
			System.out.println(bitOptionImpl.getBitsValue1(i));
			System.out.println(bitOptionImpl.getBitsValue1(i & mask));
		    buf[--charPos] = digits[i & mask];
		    i >>>= shift;
			System.out.println("________");
		} while (i != 0);
		for(int j = 0; j < 32; j++){
			System.out.print(buf[j]);
		}
		System.out.println();
		return new String(buf, charPos, (32 - charPos));
	    }
	public static void main(String[] args) {
		Test test = new Test();
//		test.testQuFan();
//		System.out.println(test.toUnsignedString(0x56a3, 1));
		char[]ca = new char[3];
		ca[2] = '2';
		ca[1] = '1';
		ca[0] = '0';
		System.out.println(new String(ca,1,2));
		int a = 2;
		int b = --a;
		System.out.println(b);
		a = 2;
		b = a--;
		System.out.println(a--);
		a = 2;
		System.out.println(ca[--a]);
//		System.out.println(Integer.toBinaryString(0x56a3));
//		test.testLeftMove();
//		test.testRightMove();
		/*int position = 4;
		int value2 = 0x1;
		value2 = value2 << (position - 1);
		System.out.println(value2);*/
	}

}

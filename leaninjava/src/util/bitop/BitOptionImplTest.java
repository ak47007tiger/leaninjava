package bitop;

import org.junit.Test;

public class BitOptionImplTest {

	@Test
	public void testGetBitByPosition() {
		BitOptionImpl bitOptionImpl = new BitOptionImpl();
		byte temp = (byte)bitOptionImpl.getBitByPosition(4, 0xf8);
		System.out.println("testGetBitByPosition");
		System.out.println(temp);
	}
	@Test
	public void testGetBitByPosition1() {
		BitOptionImpl bitOptionImpl = new BitOptionImpl();
		System.out.println("testGetBitByPosition1");
		byte temp = (byte)bitOptionImpl.getBitByPosition1(4, 0xf8);
		System.out.println(temp);
	}
	@Test
	public void getBitsValue() {
		BitOptionImpl bitOptionImpl = new BitOptionImpl();
		String temp = bitOptionImpl.getBitsValue(0xff);
		/*for(int i = 0; i < 1000000;i++){
			bitOptionImpl.getBitsValue(0xff);
		}*/
		System.out.println(temp);
	}
	@Test
	public void getBitsValue1() {
		BitOptionImpl bitOptionImpl = new BitOptionImpl();
		String temp = bitOptionImpl.getBitsValue1(0xff);
		/*for(int i = 0; i < 1000000;i++){
			bitOptionImpl.getBitsValue1(0xff);
		}*/
		System.out.println(temp);
	}
	@Test
	public void testAnd(){
		int a = 0x1;
		int c = 0;
		for(int i = 0; i < 100000000; i ++){
			c = a & 0xffff;
		}
	}
	@Test
	public void testMove(){
		int a = 0x1;
		int c = 0;
		for(int i = 0; i < 100000000; i ++){
			c = a<<2;
		}
	}

}

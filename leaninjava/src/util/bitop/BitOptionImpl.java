package bitop;

public class BitOptionImpl implements IBitOption {

	/**
	 * position 为从右边到左边的位数 1101 1(position is 4,value is 8)011 把一个32位数的指定位取反
	 */
	@Override
	public int opsideonebit(int position, int value) {
		// 把一位数字与1异或就是取反，与0异或就是不变
		//同样的位数，取反的为1，不变的为0，构造这样一个数来异或运算
		int value2 = 0x1;
		return value ^ (value2 << (position - 1));
	}

	public static void main(String[] args) {
		int a = BitOptionImpl.MAX + 1;
		System.out.println(a);
		new BitOptionImpl().getBitsValue1(0x8);
	}

	/**
	 * 先左移，后右移 1101 1(position is 4,value is 8)011
	 */
	@Override
	public int getBitByPosition(int position, int value) {
		return (value << (MAXPOSITION - position)) >>> (MAXPOSITION - 1);
	}
	/**
	 * 先将无关位置0，再把目标位移到最右端
	 */
	@Override
	public int getBitByPosition1(int position, int value) {
		return (value & (1 << (position - 1))) >>> (position - 1);
	}
	@Override
	public String getBitsValue(int value){
		StringBuffer bits = new StringBuffer();
		for(int i = MAXPOSITION; i >= 1; i--){
			bits.append(getBitByPosition(i, value));
		}
		return bits.toString();
	}

	/**
	 * 这个方法有着更高的效率
	 */
	@Override
	public String getBitsValue1(int value) {
		return Integer.toBinaryString(value);
	}
	
}

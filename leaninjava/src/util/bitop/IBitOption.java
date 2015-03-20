package bitop;

public interface IBitOption {

	//对某一位取反
	int opsideonebit(int position,int value);
	int getBitByPosition(int position,int value);
	int getBitByPosition1(int position, int value);
	String getBitsValue(int value);
	String getBitsValue1(int value);
	int MAXPOSITION = 32;
	int MAX = 0xffffffff;//1111 1111 1111 1111 1111 1111 1111 1111
	int value = MAX - 1;//1111 1111 1111 1111 1111 1111 1111 1110
	int MIN = 0x0;
}

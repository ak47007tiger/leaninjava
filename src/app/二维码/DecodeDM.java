package 二维码;

public class DecodeDM {

	public static void main(String[] args) {
		TwoDimensionCode twoDimensionCode = new TwoDimensionCode();
		System.out.println(twoDimensionCode.decoderQRCode("E:/backup/文化馆app原型.jpg"));
	}
}

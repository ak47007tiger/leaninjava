package 二维码;

public class DecodeDM {

	public static void main(String[] args) {
		TwoDimensionCode twoDimensionCode = new TwoDimensionCode();
		System.out.println(twoDimensionCode.decoderQRCode("d:/207679.png"));
	}
}

package 异常;

public class MException{

	public void test(int i) throws Exception{
		Exception exception = new Exception("i < -1");
		if(-1 > i){
			throw exception;
		}else{
			System.out.println("ok");
		}
	}
	public static void main(String[] args){
		try {
			new MException().test(-3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

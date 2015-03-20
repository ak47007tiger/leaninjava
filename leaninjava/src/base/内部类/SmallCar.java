package 内部类;

public class SmallCar extends Car{

	YouXiang youXiang = new YouXiang();
	public void test(){
		youXiang.setSize(12);
		super.youXiang.setSize(100);
		System.out.println(super.youXiang.getSize());
		System.out.println(this.youXiang.getSize());
		System.out.println(super.youXiang.getSize());
		
		new Cheer().getClass();
	}
	public static void main(String[] args) {
		SmallCar smallCar = new SmallCar();
		smallCar.test();
	}
}

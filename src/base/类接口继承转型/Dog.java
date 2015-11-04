package 类接口继承转型;

public class Dog implements IAnnimole{
	@Override
	public void lar() {
		System.out.println("汪");
	}
	public void dog(){
		System.out.println("run");
	}
	public static void main(String[] args) {
		IAnnimole annimole = new Dog();
		annimole.lar();
		Dog dog = new Dog();
		IAnnimole annimole2 = (IAnnimole)dog;
		annimole2.lar();
		Dog dog1 = (Dog)annimole;//可以转
		dog1.dog();
		annimole = new Cat();
		Cat cat = (Cat)annimole;//不报错
		cat.lar();
/*		Cat cat = (Cat)annimole;//报错
		cat.lar();*/
		//子类可以转接口
		//接口可以转子类，条件是：接口指向一个要转的子类
	}
}

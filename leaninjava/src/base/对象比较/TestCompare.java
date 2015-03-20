package 对象比较;

public class TestCompare {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		User user1 = new User();
		user1.setName("1");
		
		User user2 = new User();
		user2.setName("2");
		
		User user3 = new User();
		user3.setName("1");
		
		User user4 = user1;
		
		boolean r1 = user1 == user2;//false
		boolean r2 = user1.equals(user2);//false
		boolean r3 = user1 == user3;//false
		/*
		 * 为什么是false
		 * 先比较了hashcode，不同所以不再比较，认为不同
		 * 需要重写equal方法
		 */
		boolean r4 = user1.equals(user3);//true
		
		boolean r5 = user1 == user4;//true
		boolean r6 = user1.equals(user4);//true
		System.out.println(user1.hashCode());
		System.out.println(user2.hashCode());
		System.out.println(user3.hashCode());
		System.out.println(user4.hashCode());
	}
}

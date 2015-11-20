package collection;

import java.util.ArrayList;

public class AddToArrayInWhile {
	public static void main(String[] args) {
		ArrayList<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		test.add("4");
		test.add("5");
		test.add("6");
		int index = 0;
		while(index < test.size()){
			int val = Integer.parseInt(test.get(index));
			if(0 == val % 2){
				test.add(index,System.currentTimeMillis() + "");
				index += 2;
			}else{
				index ++;
			}
		}
		System.out.println(test.size());
	}

}

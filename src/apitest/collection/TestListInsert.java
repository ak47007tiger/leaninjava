package collection;

import java.util.ArrayList;
import java.util.List;

public class TestListInsert {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(1, "insert");
		for(String s : list){
			System.out.println(s);
		}
	}
}

package framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ConfirmIterator {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>(Arrays.asList(
				new String[] { "1,", "2", "3", "4","5" }));
		
		p(list.iterator());

		Iterator<String> iterator = list.iterator();
		int i = 0;
		while(iterator.hasNext()){
			iterator.next();
			if(i++ == 3){
				iterator.remove();
			}
		}
		
		p(list.iterator());
	}

	static <T> void p(Iterator<T> iterator){
		while(iterator.hasNext()){
			System.out.print(iterator.next() +  ",");
		}
		System.out.println();
	}
}

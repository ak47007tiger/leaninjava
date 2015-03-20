package 泛型;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FieldF {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*? is Integer's parent*/
		List<? super Integer> list = new ArrayList<Integer>();
		/*? is child of InputStream*/
		List<? extends InputStream> lsit = new ArrayList<FileInputStream>(); 
	}
}

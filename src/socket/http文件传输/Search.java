package http文件传输;

import java.util.LinkedList;
import java.util.List;

public class Search {
	List<Integer> sunday(char[]str,char[]pattern,int baseIndex){
		List<Integer> listIndexs = new LinkedList<Integer>();
		int lenIntOfStr = str.length;
		int lenIntOfPattern = pattern.length;
		int indexMaxCompare = lenIntOfStr - lenIntOfPattern;
		int indexIntStartCompare = 0;
		int sameIndex;
		//20 19 5 4 15
		while(indexIntStartCompare <= indexMaxCompare){
			if(compare(str, pattern, indexIntStartCompare)){
				listIndexs.add(indexIntStartCompare + baseIndex);
				indexIntStartCompare++;
			}else{
				//达到最大可比较下标，仍然不相匹配
				if(indexIntStartCompare == indexMaxCompare){
					break;
				}
				indexIntStartCompare += lenIntOfPattern;
				if(-1 == (sameIndex = contain(pattern, str[indexIntStartCompare]))){
					indexIntStartCompare += 1;
				}else{
					indexIntStartCompare -= sameIndex;
				}
			}
		}
		return listIndexs;
	}
	int contain(char[]pattern,char c){
		int index = -1;
		int len = pattern.length;
		int i;
		for(i = len - 1; i >= 0; i--){
			if(c == pattern[i]){
				index = i;
				break;
			}
		}
		return index;
	}
	boolean compare(char[]str,char[]pattern,int indexIntStartCompare){
		int lenIntOfPattern = pattern.length;
		int i;
		boolean result = true;
		for(i = 0; i < lenIntOfPattern; i ++){
			if(pattern[i] != str[indexIntStartCompare + i]){
				result = false;
				break;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		String str = "asdfbrzxcvfeqazxceqqtghtqzxc";
		String patt = "zxc";
		List<Integer> l = new Search().sunday(str.toCharArray(), patt.toCharArray(),0);
		for (Object object : l) {
			int v = ((Integer)object);
			System.out.println(v);
		}
	}
}

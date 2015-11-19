package haschines;

import java.io.File;
import java.io.IOException;

public interface HasChinese {
	boolean hasChinese(File file) throws IOException;
	boolean hasChinese(char[] cbuf,int start,int length);
}

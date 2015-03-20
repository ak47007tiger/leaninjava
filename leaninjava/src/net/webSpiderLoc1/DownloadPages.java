package webSpiderLoc1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DownloadPages {

	public static void main(String[] args) {
		//download html from net
		//parse by dom4j then get a document
		//find all href of <a class="detail_line"></a> in document
		//download files from net
		DownloadPages dp = new DownloadPages();
		String url = "http://www.pudn.com/downloads178/sourcecode/java/detail827274.html";
		String htmlPath = null;
		/*try {
			htmlPath = dp.downloadFile(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}*/
		htmlPath = "e:/tans/detail827274.html";
		File htmlfile = new File(htmlPath);
		SAXReader sr = new SAXReader();
		Document htmlDoc = null;
		try {
			htmlDoc = sr.read(htmlfile);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = htmlDoc.getRootElement();
//		List<Element> tagA_list = new LinkedList<Element>();
		List<String> attrs = new LinkedList<String>();
		dp.getAttrsOfAll(root, attrs, "a", "href");
		for(Iterator<String> i = attrs.iterator(); i.hasNext();){
			String href = i.next();
			System.out.println(href);
			try {
				dp.downloadFile(new URL(href));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	public void getAttrsOfAll(final Element node,final List<String> attrs,final String tagName,final String attrName){
		if(!node.elementIterator().hasNext()){
			return;
		}
		Iterator<Element> iterator = node.elementIterator();
		for (; iterator.hasNext();) {
			Element child = iterator.next();
			String l_tagName = child.getName();
			if(tagName.equals(l_tagName)){
				Attribute attr = child.attribute("href");
				if(null != attr){
					String href = attr.getText();
					attrs.add(href.replaceAll(" ", ""));
				}
			}
			getAttrsOfAll(child, attrs, l_tagName, attrName);
		}
	}
	public void appendByTagName(Element node,List<Element> tags){
		if(!node.elementIterator().hasNext()){
			return;
		}
		Iterator<Element> iterator = node.elementIterator();
		for (; iterator.hasNext();) {
			Element child = iterator.next();
			String tagName = child.getName();
			if("a".equals(tagName) || "A".equals(tagName)){
				tags.add(child);
			}
			appendByTagName(child, tags);
		}
	}
	public static final String tempDir = "e:/tans/";
	public String downloadFile(URL url){
		String s_url = url.getFile();
		System.out.println(s_url);
		
		String fileLocPath = tempDir + s_url.substring(s_url.lastIndexOf('/') + 1);
		System.out.println(fileLocPath);
		InputStream ins = null;
		OutputStream outs = null;
		try {
			URLConnection uc = url.openConnection();
			ins = uc.getInputStream();
			outs = new FileOutputStream(new File(fileLocPath));
			byte[] b = new byte[1024];
			int count = 0;
			while ((count = ins.read(b, 0, 1024)) > 0) {
				outs.write(b, 0, count);
			}
			ins.close();
			outs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileLocPath;
	}
}

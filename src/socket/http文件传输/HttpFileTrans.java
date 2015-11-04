package http文件传输;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;

public class HttpFileTrans implements Runnable {
	private Socket socket;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public HttpFileTrans(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
//		this.cutFileLimetLenAndNum();
		this.moveAllToFile();
//		this.cutByteFile();
//		this.cutStrFile();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 方法兼容ie、谷歌浏览器
	 */
	public void cutStrFile(){
		InputStream ins = null; OutputStream outs = null;
		try {
			ins = socket.getInputStream();
			InputStreamReader insr = new InputStreamReader(ins, "gbk");
			BufferedReader br = new BufferedReader(insr);
			String cur = br.readLine();
			int boundaryIndex;
			String boundaryStr = null;//边界
			String endBoundaryStr = null;//http结束标识
			while(null != cur){
				System.out.println(cur);
				if(0 < (boundaryIndex = cur.indexOf("boundary", 0))){
					boundaryStr = "--" + cur.substring(boundaryIndex + "boundary".length() + 1);
					endBoundaryStr = boundaryStr + "--";
					break;
				}
				cur = br.readLine();
			}
			URL url = ClassLoader.getSystemResource("server");
			String parentPath = url.getFile();
			
			while (!cur.equals(endBoundaryStr)) {
				//边界
				if(boundaryStr.equals(cur)){
					cur = br.readLine();//传输项信息
					System.out.println(cur);
					//文件
					if(3 == cur.split(";").length){
						String filename = cur.substring(cur.indexOf("filename=\"") + "filename=\"".length(), cur.length() - 1);
						//ie传输时候传全路径，路径将带有\，而这样的字符不允许出现在文件名里
						int nameStart = filename.lastIndexOf("\\");
						if(0 < nameStart){
							filename = filename.substring(nameStart);
						}
						File f = new File(parentPath + "/" + filename);
						outs = new FileOutputStream(f);
						cur = br.readLine();//文件类型
						cur = br.readLine();//空行
						cur = br.readLine();//第一行文件内容
						//读到下一个边界则停止
						StringBuffer fileStr = new StringBuffer();
						while(!cur.equals(boundaryStr)&&!cur.equals(endBoundaryStr)){
//							outs.write(cur.getBytes());
							fileStr.append(cur);
							cur = br.readLine();
						}
						outs.write(fileStr.toString().getBytes("gbk"));
						outs.close();
					}
					//字符串
					else{
						cur = br.readLine();
					}
				}
				//辅助信息
				else{
					cur = br.readLine();
					System.out.println(cur);
				}
			}
			br.close();
			String msg = "传输完成";
			this.showMsgWithBuf(msg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ins) {
					ins.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void cutFileLimetLen(){
		final String charSet = "gbk";
		InputStream ins = null; 
		try {
			ins = socket.getInputStream();
			int maxLenBuf = 1024 * 1024 * 5;
			byte[]buf = new byte[maxLenBuf];
			int count;
			URL url = ClassLoader.getSystemResource("server");
			String parentPath = url.getFile();
			String msg = null;
			count = ins.read(buf, 0, maxLenBuf);
			if(maxLenBuf == count){
				msg = "上传文件过大:" + count;
				this.showMsg(msg);
				return;
			}
			byte[] real = Arrays.copyOfRange(buf, 0, count);
			String content = new String(real,charSet);
			int boundaryIndex = content.indexOf("boundary=");
			String boundaryStr = null;//边界
//			String endBoundaryStr = null;//http结束标识
			boundaryStr = "--" + content.substring(boundaryIndex + "boundary=".length(),content.indexOf("\n", boundaryIndex));
//			endBoundaryStr = boundaryStr + "--";
			String[] strFiles = content.split(boundaryStr);
			int fileCount;
			for(fileCount = 1; fileCount < strFiles.length; fileCount++){
				String strFile = strFiles[fileCount];
				int nameStart = strFile.indexOf("filename=\"") + "filename=\"".length();
				String fileName = strFile.substring(nameStart, strFile.indexOf("\"",nameStart));
				int start;
				start = strFile.indexOf("\r\n");//第一行
				start = strFile.indexOf("\r\n",++start);//第二行
				start = strFile.indexOf("\r\n",++start);//第三行
				start++;//正式的文件内容开始
				FileOutputStream byteFile = new FileOutputStream(parentPath + "/" + fileName);
				byteFile.write(strFile.substring(start).getBytes(charSet));
				byteFile.close();
			}
			String tempName = parentPath + "/" + System.currentTimeMillis()+ "temp";
			FileOutputStream fout = new FileOutputStream(tempName);
			fout.write(buf, 0, count);
			fout.close();
			this.showMsgWithBuf("传输完成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ins) {
					ins.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void cutFileLimetLenAndNum(){
		final String charset = "utf-8";
		InputStream ins = null; 
		try {
			ins = socket.getInputStream();
			int maxLenBuf = 1024 * 1024 * 5;
			byte[]buf = new byte[maxLenBuf];
			int count;
			
			String msg = null;
			count = ins.read(buf, 0, maxLenBuf);
			if(maxLenBuf == count){
				msg = "上传文件过大:" + count;
				this.showMsg(msg);
				return;
			}
			byte[] real = Arrays.copyOfRange(buf, 0, count);
			String content = new String(real,charset);
			int boundaryIndex = content.indexOf("boundary=");
			String boundaryStr = null;//边界
			String endBoundaryStr = null;//http结束标识
			boundaryStr = "--" + content.substring(boundaryIndex + "boundary=".length(),content.indexOf("\n", boundaryIndex));
			endBoundaryStr = boundaryStr + "--";
			int start = content.indexOf(boundaryStr);
			int nameStart = content.indexOf("filename=\"") + "filename=\"".length();
			String fileName = content.substring(nameStart, content.indexOf("\"",nameStart));
			start = content.indexOf("\n",start);//第1行开头
			start = content.indexOf("\n",++start);//第1行结尾
			start = content.indexOf("\n",++start);//第2行
			start = content.indexOf("\n",++start);//第3行 空行
			start++;//正式的文件内容开始
			URL url = ClassLoader.getSystemResource("server");
			String parentPath = url.getFile();
			FileOutputStream byteFile = new FileOutputStream(parentPath + "/" + fileName);
			String uselessStr = content.substring(0, start);
			int off = uselessStr.getBytes(charset).length;
			byteFile.write(real,off,count - off - endBoundaryStr.getBytes(charset).length - 2);
			byteFile.close();
			/*String tempName = parentPath + "/" + System.currentTimeMillis()+ "temp";
			FileOutputStream fout = new FileOutputStream(tempName);
			fout.write(real, 0, count);
			fout.close();*/
			this.showMsgWithBuf("传输完成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ins) {
					ins.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void showMsgWithBuf(String msg){
		try {
			StringBuffer sbf = new StringBuffer();
			sbf.append("HTTP/1.0 200 OK\n");
			sbf.append("MIME_version:1.0\n");
			sbf.append("Content_Type:text/html;charset=utf-8\n");
			sbf.append("Content_Length:" + msg.length() + "\n\r\n");
			sbf.append(msg);
			OutputStream outs = socket.getOutputStream();
			outs.write(sbf.toString().getBytes("utf-8"));
			outs.flush();
			outs.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void showMsg(String msg){
		try {
			PrintWriter pw;
			pw = new PrintWriter(socket.getOutputStream());
			pw.println("HTTP/1.0 200 OK");
			pw.println("MIME_version:1.0");
			pw.println("Content_Type:text/html;charset=utf-8");
			pw.println("Content_Length:" + msg.length());
			pw.println();//相应报文格式规定要有空行
			pw.println(msg);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void cutByteFile(){
		InputStream ins = null; OutputStream outs = null;
		try {
			ins = socket.getInputStream();
			int maxLenBuf = 1024 * 5;
			byte[]buf = new byte[maxLenBuf];
			int count;
			URL url = ClassLoader.getSystemResource("server");
			String parentPath = url.getFile();
			String tempName = parentPath + "/" + System.currentTimeMillis()+ "temp";
			FileOutputStream fout = new FileOutputStream(tempName);
			while(0 < (count = ins.read(buf, 0, maxLenBuf))){
				fout.write(buf, 0, count);
				if(count < maxLenBuf){
					break;
				}
			}
			fout.close();
			File tempFile = new File(tempName);
			FileInputStream fin = new FileInputStream(tempFile);
			String realName;
			
			this.showMsg("传输完成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ins) {
					ins.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void moveAllToFile() {
		InputStream ins = null; OutputStream outs = null;
		try {
			ins = socket.getInputStream();
			InputStreamReader insr = new InputStreamReader(ins);
			BufferedReader br = new BufferedReader(insr);
			URL url = ClassLoader.getSystemResource("server");
			String parentPath = url.getFile();
			File f = new File(parentPath + "/" + System.currentTimeMillis()
					+ ".txt");
			outs = new FileOutputStream(f);
			String cur = br.readLine();
			String regex = "\\-{6}WebKitFormBoundary.+\\-{2}";
			while (!cur.matches(regex)) {
				outs.write(cur.getBytes(), 0, cur.length());
				outs.write("\r\n".getBytes());
				cur = br.readLine();
			}
			System.out.println(cur);
			outs.write(cur.getBytes(), 0, cur.length());
			outs.write("\r\n".getBytes());

			this.showMsg("传输完成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ins) {
					ins.close();
				}
				if (null != outs) {
					outs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

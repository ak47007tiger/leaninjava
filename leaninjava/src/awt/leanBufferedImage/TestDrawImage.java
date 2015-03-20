package leanBufferedImage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 测试：如果绘制在bufferedimage之外是不是占内存
 * @author Administrator
 *
 */
public class TestDrawImage {

	public static void main(String[] args) {
		BufferedImage bfdimg1 = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);
		BufferedImage bfdimg2 = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);
		
		URL url1 = ClassLoader.getSystemResource("awt/c.jpg");
		URL url2 = ClassLoader.getSystemResource("awt/d.jpg");
		URL url3 = ClassLoader.getSystemResource("awt/3.gif");
		URL url4 = ClassLoader.getSystemResource("awt/4.gif");
		
		Image img1 = new ImageIcon(url1).getImage();
		Image img2 = new ImageIcon(url2).getImage();
		Image img3 = new ImageIcon(url3).getImage();
		Image img4 = new ImageIcon(url4).getImage();
		
		Graphics g1 = bfdimg1.getGraphics();
		Graphics g2 = bfdimg2.getGraphics();
		
		g1.drawImage(img1, 0, 0, 500, 300, null);
//		g1.drawImage(img2, 0, 0, 500, 300, null);
		g1.dispose();
		//位置、大小都将影响buffered的大小
		//如果绘制的位置超过了buffered的大小，则图片相当于不占大小
		/* 同一个图片
		 * g1.drawImage(img1, 0, 0, 1000, 600, null);占更多存储
		 * g2.drawImage(img1, 0, 0, 500, 300, null); 占更少存储
		 */
		/*
		 * g1.drawImage(img1, 0, 0, 500, 300, null);
		 * g1.drawImage(img2, 0, 0, 500, 300, null);
		 * 第一个图片不占空间
		 */
		g2.drawImage(img1, 0, 0, 16000, 9600, null);
//		g2.drawImage(img2, 500, 100, 100, 100, null);
		g2.dispose();
		File image1 = new File("d:/image1.jpg");
		File image2 = new File("d:/image2.jpg");
		try {
			if(!image1.exists()){
					image1.createNewFile();
			}
			if(!image2.exists()){
				image2.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ImageIO.write(bfdimg1, "jpg", image1);
			ImageIO.write(bfdimg2, "jpg", image2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

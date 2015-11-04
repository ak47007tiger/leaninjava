package net;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyCanvas extends Canvas {

	private static final long serialVersionUID = 1L;

	// 窗体的宽与高

	public static final int WIDTH = 480;

	public static final int HEIGHT = 480;

	private Image screen = createImage(WIDTH, HEIGHT, true);// 双缓冲

	private Graphics graphics = screen.getGraphics();

	private Image resultImage;
	private Image cat;

	/**
	 * 
	 * 生成一个BufferImage
	 * 
	 * 生成一个BufferImage BufferImage是Image的子类，左上角坐标都为 (0, 0)
	 * 
	 * 第三个参数是代码Image图形类型，分为14种，以位数又分为1，2或4位
	 * 
	 * 
	 * 
	 * @param width
	 * 
	 * @param height
	 * 
	 * @param flag
	 * 
	 * @return
	 */

	final static public BufferedImage createImage(int width, int height,

	boolean flag) {

		if (flag) {

			return new BufferedImage(width, height, 2);

		} else {

			return new BufferedImage(width, height, 1);

		}

	}

	public MyCanvas() {

		// 设定初始构造时面板大小

		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// 初始导入一张图片
		URL url = ClassLoader.getSystemResource("awt/5.gif");
		
		ImageIcon icon = new ImageIcon(url);

		resultImage = icon.getImage();
		cat = new ImageIcon(ClassLoader.getSystemResource("awt/sererscat.gif")).getImage();
	}

	//

	@Override
	public void update(Graphics g) {

		graphics.drawImage(resultImage, 0, 0, this);
		graphics.drawImage(cat, 0, 0, this);
		g.drawImage(screen, 0, 0, null);// 最后个参数一定要用null，这样可以防止drawImage调用update方法
		g.dispose();

	}

	public void paint(Graphics g) {

		update(g);// 我们在paint方法中，直接调用update方法

	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		MyCanvas mc = new MyCanvas();
		jf.add(mc);
		jf.setBounds(200, 200, 600, 400);
		jf.setVisible(true);
	}
}

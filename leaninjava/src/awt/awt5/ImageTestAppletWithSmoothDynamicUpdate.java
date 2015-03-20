package awt5;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class ImageTestAppletWithSmoothDynamicUpdate extends Applet {
	private Image im;

	int x = 100,y = 100;
	
	public void init() {
		URL url = ClassLoader.getSystemResource("awt/sererscat.gif");
		im = Toolkit.getDefaultToolkit().getImage(url);
		System.out.print("Image width=" + im.getWidth(this));
		System.out.println(" height=" + im.getHeight(this));
		new Thread(new ChangePos()).start();
	}

	public void paint(Graphics g) {
		g.drawImage(im, x, y, this);
	}

	class ChangePos implements Runnable{

		int speed = 5;
		@Override
		public void run() {
			while(true){
				System.out.println(x + "," + y);
				if(x > 300 || x < -100){
					
					speed = -speed;
				}
				x +=  speed;
				y +=  speed;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public boolean imageUpdate(Image image, int flags, int x, int y, int w,
			int h) {
//		System.out.println("imageUpdate(): x=" + x + ", y=" + y + " w=" + w
//				+ ",h=" + h);
		repaint();

		if ((flags & ALLBITS) == 0)
			return true; // need more updates
		else
			return false; // image is fully loaded
	}

	public void update(Graphics g) {
		paint(g);
	}
}

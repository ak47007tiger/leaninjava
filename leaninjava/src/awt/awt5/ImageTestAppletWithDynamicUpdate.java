package awt5;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class ImageTestAppletWithDynamicUpdate extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image im;

	public void init() {
		im = getImage(getCodeBase(), "awt/sererscat.gif");

		System.out.print("Image width=" + im.getWidth(this));
		System.out.println(" height=" + im.getHeight(this));
	}

	public void paint(Graphics g) {
		g.drawImage(im, 0, 0, this);
	}

	public boolean imageUpdate(Image image, int flags, int x, int y, int w,
			int h) {
		System.out.println("imageUpdate(): x=" + x + ", y=" + y + " w=" + w
				+ ",h=" + h);
		repaint();

		if ((flags & ALLBITS) == 0)
			return true; // need more updates
		else
			return false; // image is fully loaded
	}
}

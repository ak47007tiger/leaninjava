package awt5;

import java.awt.*;
import java.awt.event.*;

public class Globe1 extends Frame {
	Image globe;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public static void main(String args[]) {
		Frame f = new Globe1();
		f.setVisible(true);
	}

	public Globe1() {
		super("globe1");
		globe = tk.getImage(ClassLoader.getSystemResource("awt/sererscat.gif"));
		try {
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(globe, 0);
			mt.waitForID(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}
		});
	}

	public void addNotify() {
		super.addNotify(); // this instantiates the peer

		Insets insets = getInsets();
		Dimension scrnsz = tk.getScreenSize();
		Dimension globesz = new Dimension(globe.getWidth(this),
				globe.getHeight(this));

		setBounds((scrnsz.width / 2) - (globesz.width / 2), (scrnsz.height / 2)
				- (globesz.height / 2), globesz.width + insets.left
				+ insets.right, globesz.height + insets.top + insets.bottom);
	}

	public boolean imageUpdate(Image image, int flags, int x, int y, int w,
			int h) {
		if ((flags & FRAMEBITS) != 0) {
			try {
				Thread.currentThread().sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
		return true;
	}

	public void paint(Graphics g) {
		Insets insets = getInsets();
		g.drawImage(globe, insets.left, insets.top, this);
	}

	public void update(Graphics g) {
		paint(g);
	}
}

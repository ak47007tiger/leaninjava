package awt5;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class Globe0 extends Frame {
	Image globe;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public static void main(String args[]) {
		Frame f = new Globe0();
		f.setVisible(true);
	}

	public Globe0() {
		super("globe");
		URL url = ClassLoader.getSystemResource("awt/sererscat.gif");
		globe = tk.getImage(url.getFile());
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
	class ChangePos implements Runnable{

		int speed = 5;
		@Override
		public void run() {
			repaint();
			while(true){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
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

	public void paint(Graphics g) {
		update(g);
	}

	public void update(Graphics g) {
		Insets insets = getInsets();
		g.drawImage(globe, insets.left, insets.top, this);
	}
}

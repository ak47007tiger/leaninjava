package awt24;

import java.applet.Applet;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;

public class DoubleBufferedContainerTest extends Applet {
	private DoubleBufferedContainer container;

	public void init() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		URL cb = getCodeBase();

		Image mandrill = getImage(cb, "awt/1.jpg");
		Image dining = getImage(cb, "awt/2.gif");
		Image bg = getImage(cb, "awt/3.gif");
		Image skelly = getImage(cb, "awt/4.gif");
		Image gj = getImage(cb, "awt/5.gif");

		container = new DoubleBufferedContainer();
		container.setWallpaperImage(bg);

		container.add(new Lightweight(mandrill));
		container.add(new Lightweight(skelly));
		container.add(new Lightweight(dining));
		container.add(new Lightweight(gj));
//		container.add(new Lightweight(mandrill, false));

		setLayout(new BorderLayout());
		add(container, "Center");
		show();
	}

	public void update(Graphics g) {
		paint(g);
	}
}

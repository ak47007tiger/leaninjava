package runClass;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RunClass extends JFrame {

	public RunClass() {
		init();
		initChildren();
		addListeners();
		this.setVisible(true);
	}

	private void init() {
		this.setLayout(new GridLayout(3, 1));
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screen.width;
		int y = screen.height;
		final int width = 100;
		final int height = 200;
		this.setBounds((x - width) / 2, (y - height) / 2, width, height);
		this.setTitle("run class");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initChildren() {
		// 建立菜单栏
		MenuBar menubar = new MenuBar();
		Menu menufile = new Menu("file");
		MenuItem menuopen = new MenuItem("open",
				new MenuShortcut(KeyEvent.VK_O));
		menufile.add(menuopen);
		menufile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		menubar.add(menufile);
		this.setMenuBar(menubar);
		run = new JButton("run");
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		this.add(run);
		JLabel lab = new JLabel("filePath:");
		this.add(lab);
		jtf_filePath = new JTextField("");
		this.add(jtf_filePath);
	}

	JButton run;
	String dir;
	String name;
	JTextField jtf_filePath;

	protected void open() {
		FileDialog dialog = new FileDialog(this, "Open", 0);
		dialog.setVisible(true);
		dir = dialog.getDirectory();
		name = dialog.getFile();
		jtf_filePath.setText(dir + name);
		System.out.println("________________________");
		System.out.println(EncodeString.getByEncode(dir, "utf-8", "gbk"));
		System.out.println(EncodeString.getByEncode(dir, "gbk", "gbk"));
		System.out.println(EncodeString.getByEncode(dir, "gbk", "utf-8"));
		System.out.println("________________________");
	}

	protected void run() {
		if (name != null) {
			String nameNoSuf = name.substring(0, name.lastIndexOf("."));
			OutputStream os = null;
			File bat = null;
			try {
				bat = new File(dir + nameNoSuf + ".bat");
				os = new FileOutputStream(bat);
				String[] command = new String[3];
				command[0] = new String(dir.substring(0, 2).getBytes("gbk"),"gbk") + "\r\n";
				command[1] = "cd " + new String(dir.substring(0, dir.length() - 1).getBytes("gbk"),"gbk")
						+ "\r\n";
				command[2] = "java " + new String(nameNoSuf.getBytes("gbk"),"gbk") + "\r\n";
				for (int i = 0; i < command.length; i++) {
					os.write(command[i].getBytes("gbk"));
//					os.write(command[i].getBytes());
				}
				System.out.println(bat.getParent());
				System.out.println(bat.getPath());
				Runtime.getRuntime().exec("cmd.exe /C " + bat.getPath());
//				Runtime.getRuntime().exec(bat.getPath());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != os) {
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void callCmd(String locationCmd) {
		try {
			Process child = Runtime.getRuntime().exec(
					"cmd.exe /C start " + locationCmd);
			InputStream in = child.getInputStream();
			int c;
			while ((c = in.read()) != -1) {
			}
			in.close();
			try {
				child.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addListeners() {

	}

	public static void main(String[] args) {
		new RunClass();
	}
}

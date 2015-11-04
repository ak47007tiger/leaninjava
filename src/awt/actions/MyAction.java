package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class MyAction extends AbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = new JButton(this);
	}

}

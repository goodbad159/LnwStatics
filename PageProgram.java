import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PageProgram extends JFrame implements ActionListener {
	private JLabel icon;
	private Timer time;
	private int i = 1;
	private JPanel jp2;

	public PageProgram() {
		setSize(500, 270);
		setLocationRelativeTo(null);
		JPanel jp1 = new JPanel();
		icon = new JLabel();
		jp1.add(icon);

		jp2 = new JPanel();
		jp2.setLayout(new GridLayout(0, 50));

		add(jp1, BorderLayout.CENTER);
		add(jp2, BorderLayout.SOUTH);
		setVisible(true);

		time = new Timer(100, this);
		time.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (i > 0 && i <= 10) {
			icon.setIcon(new ImageIcon("icon\\A4.png"));
			jp2.add(new JLabel(new ImageIcon("icon\\londing.png")));
			validate();
			validate();
		} else if (i > 10 && i <= 50) {
			icon.setIcon(new ImageIcon("icon\\lnwStatic .png"));
			jp2.add(new JLabel(new ImageIcon("icon\\londing.png")));
			validate();
		}
		if (i == 50) {
			dispose();
			time.stop();
		}
		i++;
	}

}

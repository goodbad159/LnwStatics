import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PageLogIn extends JFrame {
	private static AuthenticationController db;
	private JTextField username;
	private JPasswordField password;
	private JButton signin;
	private JButton login;
	private String nameSub;

	public PageLogIn() {
		try {
			db = new AuthenticationController();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setTitle("Signin / Login");
		setSize(350, 350);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel jpBig = new JPanel();
		jpBig.setLayout(new GridLayout(5, 0));

		ImageIcon im = new ImageIcon(getClass().getResource("projectSe_V2/icon/lnwStatic .png"));
		JLabel jl = new JLabel(im);
		add(jl, BorderLayout.NORTH);

		JPanel jp1 = new JPanel();
		Label jl1 = new Label("Username");
		username = new JTextField(10);
		jp1.add(jl1);
		jp1.add(username);

		JPanel jp2 = new JPanel();
		Label jl2 = new Label("Password");
		password = new JPasswordField(10);
		jp2.add(jl2);
		jp2.add(password);

		JPanel jp4 = new JPanel();
		signin = new JButton("Signup");
		login = new JButton("Login");
		jp4.add(signin);
		jp4.add(login);

		jpBig.add(jp1);
		jpBig.add(jp2);
		jpBig.add(jp4);

		signin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signInPage();
			}
		});

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (createTable(username.getText()) == 0) {
					new ProjectFrame(username.getText());

				} else {
					JOptionPane.showMessageDialog(null, "ไม่มี Username นี้");
				}
			}
		});

		add(jpBig, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void signInPage() {
		JTextField user;
		JTextField nsub;
		JButton cku;
		JButton ckp;
		JPasswordField pass;
		JPasswordField conPass;
		setEnabled(false);
		JFrame j = new JFrame("Signup");
		j.setPreferredSize(new Dimension(300, 280));
		j.setResizable(false);
		j.setLocationRelativeTo(null);
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(6, 0));

		JPanel jp1b = new JPanel();
		jp1b.setLayout(new BorderLayout());
		JPanel jp1 = new JPanel();
		Label jl1 = new Label("Username");
		jl1.setAlignment(jl1.LEFT);
		user = new JTextField(10);

		jp1.add(jl1);
		jp1.add(user);
		jp1b.add(jp1, BorderLayout.CENTER);

		JPanel jp2 = new JPanel();
		Label jl2 = new Label("Password");
		pass = new JPasswordField(10);
		JLabel w = new JLabel(
				"<html>" + "password ต้องมีอย่างน้อย 8 ตัว " + "<br>" + "ประกอบด้วย  a-z,0-9" + "</html>");
		w.setHorizontalAlignment(JLabel.CENTER);
		jp2.add(jl2);
		jp2.add(pass);

		JPanel jp3b = new JPanel();
		jp3b.setLayout(new BorderLayout());
		JPanel jp3 = new JPanel();
		Label jl3 = new Label("PasswordConfirm");
		jl3.setAlignment(JLabel.LEFT);
		conPass = new JPasswordField(10);

		jp3.add(jl3);
		jp3.add(conPass);

		jp3b.add(jp3, BorderLayout.CENTER);

		JPanel jp4 = new JPanel();
		Label jsub = new Label("NameSubject");
		jsub.setAlignment(JLabel.LEFT);
		nsub = new JTextField(10);
		jp4.add(jsub);
		jp4.add(nsub);

		JPanel jp5 = new JPanel();
		JButton ok = new JButton("ยืนยัน");

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chackUsername(user.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Username นี้ไม่ใช้ได้");
					user.setText("");
				}
				if (chackPassword(pass.getText(), conPass.getText()) == false) {
					JOptionPane.showMessageDialog(null, "Password ผิด");
					pass.setText("");
					conPass.setText("");
				}
				if (nameSubjectNull(nsub.getText()) == 0) {
					JOptionPane.showMessageDialog(null, "กรุณากรอกรหัสวิชา");
				} else if(chackUsername(user.getText()+pass.getText()) == true&&chackPassword(pass.getText(), conPass.getText()) == true&&nameSubjectNull(nsub.getText()) == 1) {
					JOptionPane.showMessageDialog(null, "ลงทะเบียนเรียบร้อย" + "");
					nameSub = nsub.getText();
					setEnabled(true);
					j.dispose();
				}
			}
		});

		jp5.add(ok);

		jp.add(jp1b);
		jp.add(jp2);
		jp.add(w);
		jp.add(jp3b);
		jp.add(jp4);
		jp.add(jp5);

		j.add(jp);
		j.setVisible(true);
		j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
		j.pack();

	}

	public static boolean chackUsername(String username) {
		int n = createTable(username);
		if (n == 1) {
			return true;
		}
		return false;
	}

	public static int createTable(String nameTable) {
		try {
			if (!nameTable.equals("")) {
				db.createTable(nameTable);
				return 1;
			}
		} catch (SQLException e) {

		}
		return 0;
	}

	public static boolean chackPassword(String pss, String pssC) {
		String p = "[a-z\\d]{8,16}";
		if (pss.matches(p) && pss.equals(pssC)) {
			return true;
		}
		return false;
	}

	public static int nameSubjectNull(String ns) {
		if (ns.equalsIgnoreCase("")) {
			return 0;
		}
		return 1;
	}

	public static void main(String[] args) {
		new PageLogIn();
	}

}

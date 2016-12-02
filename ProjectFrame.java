import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

import org.jfree.ui.RefineryUtilities;

public class ProjectFrame extends JFrame {
	private JTextField nameSubject;
	private JTextField numAllStudents;
	private JTextField comeStudents;
	private JTextField notComeStudents;
	private JTextField lateStudents;
	private JButton confirm;
	private JTextField numOfClass;
	private JButton calculate;
	private ArrayList<OneClass> list;
	private OneClass oclass;
	private static AuthenticationController db;

	public ProjectFrame(String id) {
		setLocationRelativeTo(null);
		connectToDatabase();
		list = new ArrayList<>();
		setTitle("<-lnwStatic->");
		setLayout(new BorderLayout());
		setSize(650, 250);
		setResizable(false);
		JPanel jp1 = new JPanel();
		JLabel b1 = new JLabel("รหัสวิชา:                                         ");
		nameSubject = new JTextField(10);
		jp1.add(b1);
		jp1.add(nameSubject);

		JPanel jp2 = new JPanel();
		JLabel b2 = new JLabel("จำนวนนักเรัยนทั้งหมด:              ");
		numAllStudents = new JTextField(10);
		jp2.add(b2);
		jp2.add(numAllStudents);

		JPanel jp3 = new JPanel();
		JLabel b3 = new JLabel("จำนวนนักเรัยนที่เข้าเรัยน:        ");
		comeStudents = new JTextField(10);
		comeStudents.setToolTipText("กรณีไม่มีข้อมูลส่วนี้ให้ใส่ 0");
		jp3.add(b3);
		jp3.add(comeStudents);

		JPanel jp4 = new JPanel();
		JLabel b4 = new JLabel("จำนวนนักเรัยนที่มาสาย:            ");
		lateStudents = new JTextField(10);
		lateStudents.setToolTipText("กรณีไม่มีข้อมูลส่วนี้ให้ใส่ 0");
		jp4.add(b4);
		jp4.add(lateStudents);

		JPanel jp5 = new JPanel();
		JLabel b5 = new JLabel("จำนวนนักเรัยนที่ไม่เข้าเรียน:   ");
		notComeStudents = new JTextField(10);
		notComeStudents.setToolTipText("กรณีไม่มีข้อมูลส่วนี้ให้ใส่ 0");
		jp5.add(b5);
		jp5.add(notComeStudents);

		JPanel jp6 = new JPanel();
		confirm = new JButton("ยืนยันข้อมูล");
		confirm.setToolTipText("กด : โปรแกรมจะเก็บข้อมูลที่ท่านกรอกมา เพื่อรอการคำนวณ");
		jp6.add(confirm);

		JPanel jp7 = new JPanel();
		jp7.setLayout(new GridLayout(6, 0));
		jp7.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "กรอกข้อมูลและบันทึกข้อมูล"));
		jp7.add(jp1);
		jp7.add(jp2);
		jp7.add(jp3);
		jp7.add(jp5);
		jp7.add(jp4);
		jp7.add(jp6);

		// JPanel jp8 = new JPanel();
		// JLabel b8 = new JLabel("จำนวณคาบที่ต้องการคำนวณ: ");
		// numOfClass = new JTextField(10);
		// jp8.add(b8);
		// jp8.add(numOfClass);

		JPanel jp9 = new JPanel();
		calculate = new JButton("คำนวณ");
		calculate.setToolTipText("กด :โปรแกรมจะทำการคำนวณข้อมูล");
		calculate.setEnabled(false);
		jp9.add(calculate);

		JPanel jp10 = new JPanel();
		jp10.setBorder(BorderFactory.createEtchedBorder());
		// jp10.add(jp8);
		jp10.add(jp9);

		JPanel jp11 = new JPanel();
		Calendar date = new GregorianCalendar();
		jp11.setLayout(new GridLayout());
		jp11.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "วันนี้"));
		int m = date.get(Calendar.MONTH);
		JLabel dateToday = new JLabel(
				date.get(Calendar.DAY_OF_MONTH) + " / " + (m + 1) + " / " + date.get(Calendar.YEAR));
		dateToday.setFont(new Font(null, Font.BOLD, 30));
		dateToday.setHorizontalAlignment(JLabel.CENTER);
		dateToday.setForeground(Color.BLUE);
		jp11.add(dateToday);

		JPanel jp12 = new JPanel();
		jp12.setLayout(new GridLayout(2, 1));
		jp12.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "คำนวณข้อมูล"));
		jp12.add(jp10);
		jp12.add(jp11);

		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (numAllStudentsNull(numAllStudents.getText()) == 0) {
					JOptionPane j = new JOptionPane();
					j.showMessageDialog(null, "กรุณากรองจำนวนนักเรียนทั้งหมด");
				}
				if (numAllStudentsIsNumber(numAllStudents.getText()) == 1
						&& numComeStudentsIsNumber(comeStudents.getText()) == 1
						&& numLateStudentsIsNumber(lateStudents.getText()) == 1
						&& numNotComeStudentsIsNumber(notComeStudents.getText()) == 1) {
					int as = Integer.valueOf(numAllStudents.getText());
					int cs = Integer.valueOf(comeStudents.getText());
					int ls = Integer.valueOf(lateStudents.getText());
					int ncs = Integer.valueOf(notComeStudents.getText());
					int sum = cs + ls + ncs;
					if (numAllStudentsFully(as, sum) > 0) {
						JOptionPane j = new JOptionPane();
						j.showMessageDialog(null, "จำนวนนักเรียนขาด" + " " + (as - sum) + " คน" + "\n"
								+ "จาก  จำนวนนักเรัยนทั้งหมด  " + as + " คน");
						comeStudents.setText("");
						lateStudents.setText("");
						notComeStudents.setText("");
					} else if (numAllStudentsFully(as, sum) < 0) {
						JOptionPane j = new JOptionPane();
						j.showMessageDialog(null, "จำนวนนักเรียนเกิน" + " " + (sum - as) + " คน" + "\n"
								+ "จาก  จำนวนนักเรัยนทั้งหมด  " + as + " คน");
						comeStudents.setText("");
						lateStudents.setText("");
						notComeStudents.setText("");
					} else {
						setEnabled(false);
						JFrame j = new JFrame("ข้อมูลของท่าน");
						j.setLocationRelativeTo(null);
						j.setSize(315, 250);
						j.setLayout(new GridLayout(6, 0));
						JLabel l1 = new JLabel(
								"  รหัสวิชา:                                         " + nameSubject.getText());
						JLabel l2 = new JLabel("  จำนวนนักเรัยนทั้งหมด:              " + as + "          คน");
						JLabel l3 = new JLabel("  จำนวนนักเรัยนที่เข้าเรัยน:         " + cs + "          คน");
						JLabel l4 = new JLabel("  จำนวนนักเรัยนที่ไม่เข้าเรียน:   " + ncs + "          คน");
						JLabel l5 = new JLabel("  จำนวนนักเรัยนที่มาสาย:             " + ls + "          คน");
						JButton ok = new JButton("ใช่");
						JButton cancel = new JButton("เปลี่ยนข้อมูลใหม่");
						j.add(l1);
						j.add(l2);
						j.add(l3);
						j.add(l4);
						j.add(l5);
						JPanel jp = new JPanel();
						jp.add(ok);
						jp.add(cancel);
						j.add(jp);
						j.setVisible(true);

						j.addWindowListener(new WindowListener() {

							@Override
							public void windowOpened(WindowEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void windowIconified(WindowEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void windowDeiconified(WindowEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void windowDeactivated(WindowEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void windowClosing(WindowEvent e) {
								j.dispose();
								setEnabled(true);
								nameSubject.setText("");
								numAllStudents.setText("");
								comeStudents.setText("");
								lateStudents.setText("");
								notComeStudents.setText("");
							}

							@Override
							public void windowClosed(WindowEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void windowActivated(WindowEvent e) {
								// TODO Auto-generated method stub

							}
						});

						ok.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (insertToDatabase(nameSubject.getText(), as, cs, ncs, ls, id) == 1) {
									System.out.println(1);
								} else {
									System.out.println(0);
								}
								j.dispose();
								setEnabled(true);
								calculate.setEnabled(true);

							}
						});
						cancel.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								j.dispose();
								setEnabled(true);
								nameSubject.setText("");
								numAllStudents.setText("");
								comeStudents.setText("");
								lateStudents.setText("");
								notComeStudents.setText("");
								calculate.setEnabled(false);

							}
						});
						oclass = new OneClass(nameSubject.getText(), numAllStudents.getText(), comeStudents.getText(),
								notComeStudents.getText(), lateStudents.getText());
						list.add(oclass);

					}
				} else {
					JOptionPane j = new JOptionPane();
					j.showMessageDialog(null,
							"ข้อมูลตรงส่วนของ " + "\n" + "  - จำนวนนักเรัยนทั้งหมด" + "\n"
									+ "  - จำนวนนักเรัยนที่เข้าเรัยน" + "\n" + "  - จำนวนนักเรัยนที่ไม่เข้าเรียน" + "\n"
									+ "  - จำนวนนักเรัยนที่มาสาย" + "\n" + "จะต้องเป็นตัวเลข ทั้ง 4 ส่วน");
					numAllStudents.setText("");
					comeStudents.setText("");
					lateStudents.setText("");
					notComeStudents.setText("");
				}
			}
		});
		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnabled(false);
				int[] num = selectInDatabase(nameSubject.getText(), id);
				double as = Double.valueOf(num[0]);
				double cs = Double.valueOf(num[1]);
				double ls = Double.valueOf(num[2]);
				double ncs = Double.valueOf(num[3]);
				double percs = cs / as * 100;
				double perls = ls / as * 100;
				double perncs = ncs / as * 100;
				JFrame j = new JFrame("ผลการคำนวณ");
				j.setLocationRelativeTo(null);
				j.setSize(425, 250);
				JPanel jp = new JPanel();
				jp.setLayout(new GridLayout(4, 0));
				jp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ผลการคำนวณ"));
				JLabel l1 = new JLabel("  จำนวนนักเรัยนทั้งหมด:              " + 100 + "  %");
				JLabel l2 = new JLabel("  จำนวนนักเรัยนที่เข้าเรัยน:         " + percs * 1.00 + "  %");
				JLabel l3 = new JLabel("  จำนวนนักเรัยนที่ไม่เข้าเรียน:   " + perncs + "  %");
				JLabel l4 = new JLabel("  จำนวนนักเรัยนที่มาสาย:             " + perls + "  %");
				jp.add(l1);
				jp.add(l2);
				jp.add(l3);
				jp.add(l4);

				JPanel jp2 = new JPanel();
				JComboBox<String> typeGragh = new JComboBox<>();
				typeGragh.addItem("กราฟแท่ง");
				typeGragh.addItem("กราฟวงกลม");
				JComboBox<String> dimension = new JComboBox<>();
				dimension.addItem("2D");
				dimension.addItem("3D");
				JButton ok = new JButton("Ok");
				JButton cancel = new JButton("Cancel");
				jp2.add(typeGragh);
				jp2.add(dimension);
				jp2.add(ok);
				jp2.add(cancel);

				ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						j.setEnabled(false);
						if (typeGragh.getSelectedIndex() == 0 && dimension.getSelectedIndex() == 0) {
							BarChart2DShow chart = new BarChart2DShow(percs, perls, perncs);
							chart.setLocationRelativeTo(null);
							JMenuBar jmb = new JMenuBar();
							JMenuItem jm1 = new JMenuItem();
							jm1.setIcon(new ImageIcon("icon\\save.png"));
							jm1.setToolTipText("บันทึกเป็นภาพ");
							JMenuItem jm2 = new JMenuItem();
							jm2.setIcon(new ImageIcon("icon\\back.png"));
							jm2.setToolTipText("กลับเลือกกราฟใหม่");
							JMenuItem jmie = new JMenuItem();
							jmie.setIcon(new ImageIcon("icon\\inputData.png"));
							jmie.setToolTipText("กรอกข้อมูลใหม่");

							jm1.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									BarChart2DSave bc2d = new BarChart2DSave(percs, perls, perncs);
								}
							});
							jm2.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									chart.dispose();
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jmie.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									chart.dispose();
									j.dispose();
									nameSubject.setText("");
									numAllStudents.setText("");
									comeStudents.setText("");
									lateStudents.setText("");
									notComeStudents.setText("");
									calculate.setEnabled(false);
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jmb.add(jm1);
							jmb.add(jm2);
							jmb.add(jmie);
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							chart.setJMenuBar(jmb);
							chart.pack();
							RefineryUtilities.centerFrameOnScreen(chart);
							chart.setVisible(true);
						} else if (typeGragh.getSelectedIndex() == 0 && dimension.getSelectedIndex() == 1) {
							BarChart3DShow chart = new BarChart3DShow(percs, perls, perncs);
							chart.setLocationRelativeTo(null);
							JMenuBar jmb = new JMenuBar();
							JMenuItem jm1 = new JMenuItem();
							jm1.setIcon(new ImageIcon("icon\\save.png"));
							jm1.setToolTipText("บันทึกเป็นภาพ");
							JMenuItem jm2 = new JMenuItem();
							jm2.setIcon(new ImageIcon("icon\\back.png"));
							jm2.setToolTipText("กลับเลือกกราฟใหม่");
							JMenuItem jmie = new JMenuItem();
							jmie.setIcon(new ImageIcon("icon\\inputData.png"));
							jmie.setToolTipText("กรอกข้อมูลใหม่");

							jm1.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									BarChart3DSave bc2d = new BarChart3DSave(percs, perls, perncs);
									chart.dispose();
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jm2.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									chart.dispose();
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jmie.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									chart.dispose();
									j.dispose();
									nameSubject.setText("");
									numAllStudents.setText("");
									comeStudents.setText("");
									lateStudents.setText("");
									notComeStudents.setText("");
									calculate.setEnabled(false);
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jmb.add(jm1);
							jmb.add(jm2);
							jmb.add(jmie);
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							chart.setJMenuBar(jmb);
							chart.pack();
							RefineryUtilities.centerFrameOnScreen(chart);
							chart.setVisible(true);
						} else if (typeGragh.getSelectedIndex() == 1 && dimension.getSelectedIndex() == 0) {
							PieChart2DShow demo = new PieChart2DShow(percs, perls, perncs);
							demo.setLocationRelativeTo(null);
							JMenuBar jmb = new JMenuBar();
							JMenuItem jm1 = new JMenuItem();
							jm1.setIcon(new ImageIcon("icon\\save.png"));
							jm1.setToolTipText("บันทึกเป็นภาพ");
							JMenuItem jm2 = new JMenuItem();
							jm2.setIcon(new ImageIcon("icon\\back.png"));
							jm2.setToolTipText("กลับเลือกกราฟใหม่");
							JMenuItem jmie = new JMenuItem();
							jmie.setIcon(new ImageIcon("icon\\inputData.png"));
							jmie.setToolTipText("กรอกข้อมูลใหม่");

							jm1.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									PieChart2DSave pc2d = new PieChart2DSave(percs, perls, perncs);
									demo.dispose();
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jm2.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									demo.dispose();
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jmie.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									demo.dispose();
									j.dispose();
									nameSubject.setText("");
									numAllStudents.setText("");
									comeStudents.setText("");
									lateStudents.setText("");
									notComeStudents.setText("");
									calculate.setEnabled(false);
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jmb.add(jm1);
							jmb.add(jm2);
							jmb.add(jmie);
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							demo.setJMenuBar(jmb);
							demo.setSize(560, 367);
							RefineryUtilities.centerFrameOnScreen(demo);
							demo.setVisible(true);
						} else if (typeGragh.getSelectedIndex() == 1 && dimension.getSelectedIndex() == 1) {
							PieChart3DShow demo = new PieChart3DShow(percs, perls, perncs);
							demo.setLocationRelativeTo(null);
							JMenuBar jmb = new JMenuBar();
							JMenuItem jm1 = new JMenuItem();
							jm1.setIcon(new ImageIcon("icon\\save.png"));
							jm1.setToolTipText("บันทึกเป็นภาพ");
							JMenuItem jm2 = new JMenuItem();
							jm2.setIcon(new ImageIcon("icon\\back.png"));
							jm2.setToolTipText("กลับเลือกกราฟใหม่");
							JMenuItem jmie = new JMenuItem();
							jmie.setIcon(new ImageIcon("icon\\inputData.png"));
							jmie.setToolTipText("กรอกข้อมูลใหม่");

							jm1.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									PieChart3DSave pc2d = new PieChart3DSave(percs, perls, perncs);
									demo.dispose();
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jm2.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									demo.dispose();
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jmie.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent arg0) {
									demo.dispose();
									j.dispose();
									nameSubject.setText("");
									numAllStudents.setText("");
									comeStudents.setText("");
									lateStudents.setText("");
									notComeStudents.setText("");
									calculate.setEnabled(false);
									j.setEnabled(true);
									setEnabled(true);
								}
							});
							jmb.add(jm1);
							jmb.add(jm2);
							jmb.add(jmie);
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							jmb.add(new JMenuItem());
							demo.setJMenuBar(jmb);
							demo.setSize(560, 367);
							RefineryUtilities.centerFrameOnScreen(demo);
							demo.setVisible(true);
						}

					}
				});
				cancel.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						j.dispose();
						setEnabled(true);
						nameSubject.setText("");
						numAllStudents.setText("");
						comeStudents.setText("");
						lateStudents.setText("");
						notComeStudents.setText("");
						calculate.setEnabled(false);

					}
				});

				j.add(jp, BorderLayout.CENTER);
				j.add(jp2, BorderLayout.SOUTH);
				j.setVisible(true);

			}
		});

		add(jp7, BorderLayout.WEST);
		add(jp12, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static int numAllStudentsNull(String as) {
		if (as.equalsIgnoreCase("")) {
			return 0;
		}
		return 1;
	}

	public static int numAllStudentsIsNumber(String as) {
		try {
			int n = Integer.valueOf(as);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int numComeStudentsIsNumber(String as) {
		try {
			int n = Integer.valueOf(as);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int numLateStudentsIsNumber(String as) {
		try {
			int n = Integer.valueOf(as);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int numNotComeStudentsIsNumber(String as) {
		try {
			int n = Integer.valueOf(as);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int numAllStudentsFully(int as, int sum) {
		if (as > sum)
			return 1;
		else if (as < sum)
			return -1;
		return 0;
	}

	public static int insertToDatabase(String ns, int as, int cs, int ncs, int ls, String id) {
		try {
			db.insertData(ns, as, cs, ncs, ls, id);
			return 1;
		} catch (SQLException e1) {
			return 0;
		}
	}

	public static int connectToDatabase() {
		try {
			db = new AuthenticationController();
			return 1;
		} catch (Exception e1) {
			return 0;
		}
	}

	public static int[] selectInDatabase(String ns, String id) {
		int[] num = new int[4];
		try {
			String[] s = db.getInfo(ns, id);
			for (int i = 0; i < s.length; i++) {
				num[i] = Integer.valueOf(s[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;

	}
}

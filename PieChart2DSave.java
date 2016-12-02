import java.io.*;

import javax.swing.JFileChooser;

import java.io.*;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart2DSave {
	private static double cs;
	private static double cls;
	private static double ncs;

	public PieChart2DSave(double cs, double cls, double ncs) {
		this.cs = cs;
		this.cls = cls;
		this.ncs = ncs;
		final String comeStudents = "�ѡ���¹���������¹";
		final String comeLateStudents = "�ѡ���¹��������";
		final String notcomeStudents = "�ѡ���¹���Ҵ���¹";
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(notcomeStudents, new Double(ncs));
		dataset.setValue(comeLateStudents, new Double(cls));
		dataset.setValue(comeStudents, new Double(cs));

		JFreeChart chart = ChartFactory.createPieChart("����ૹ��ͧ�ѡ���¹㹡����������Ԩ����", dataset, true, true,
				false);

		int width = 640;
		int height = 480;
		JFileChooser ch = new JFileChooser();
		int op = ch.showSaveDialog(null);
		File pieChart;
		if (op == JFileChooser.APPROVE_OPTION) {
			pieChart = ch.getSelectedFile();
			try {
				String pieChart2D = pieChart.getAbsolutePath()+".jpeg";
				File file = new File(pieChart2D);
				ChartUtilities.saveChartAsJPEG(file, chart, width, height);
			} catch (Exception e) {

			}
		}

	}
}

import java.io.*;

import javax.swing.JFileChooser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;

public class BarChart3DSave {

	public BarChart3DSave(double cs, double cls, double ncs) {
		final String comeStudents = "นักเรียนที่เข้าเรียน";
		final String comeLateStudents = "นักเรียนที่มาสาย";
		final String notcomeStudents = "นักเรียนที่ขาดเรียน";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(ncs, notcomeStudents, "");
		dataset.addValue(cls, comeLateStudents, "");
		dataset.addValue(cs, comeStudents, "");

		JFreeChart barChart = ChartFactory.createBarChart3D("เปอร์เซนต์ของนักเรียนในการเข้าร่วมกิจกรรม",
				"สถานะการเข้าร่วม", "เปอร์เซนต์", dataset, PlotOrientation.VERTICAL, true, true, false);

		int width = 640; 
		int height = 480; 
		JFileChooser ch = new JFileChooser();
		int op = ch.showSaveDialog(null);
		File barChart3D;
		if (op == JFileChooser.APPROVE_OPTION) {
			barChart3D = ch.getSelectedFile();
			try {
				String barChart3 = barChart3D.getAbsolutePath() + ".jpeg";
				File file = new File(barChart3);
				ChartUtilities.saveChartAsJPEG(file, barChart, width, height);
			} catch (Exception e) {

			}
		}
	}

}
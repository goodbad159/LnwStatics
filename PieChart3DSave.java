
import java.io.*;

import javax.swing.JFileChooser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartUtilities;

public class PieChart3DSave {
	public PieChart3DSave(double cs, double cls, double ncs) {
		final String comeStudents = "นักเรียนที่เข้าเรียน";
		final String comeLateStudents = "นักเรียนที่มาสาย";
		final String notcomeStudents = "นักเรียนที่ขาดเรียน";
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue(notcomeStudents, new Double(ncs));
		dataset.setValue(comeLateStudents, new Double(cls));
		dataset.setValue(comeStudents, new Double(cs));

		JFreeChart chart = ChartFactory.createPieChart3D("เปอร์เซนต์ของนักเรียนในการเข้าร่วมกิจกรรม", // chart																			// title
				dataset, true, true, false);

		final PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(270);
		plot.setForegroundAlpha(0.60f);
		plot.setInteriorGap(0.02);
		int width = 640;
		int height = 480;
		JFileChooser ch = new JFileChooser();
		int op = ch.showSaveDialog(null);
		File pieChart3D;
		if (op == JFileChooser.APPROVE_OPTION) {
			pieChart3D = ch.getSelectedFile();
			try {
				String pieChart3 = pieChart3D.getAbsolutePath() + ".jpeg";
				File file = new File(pieChart3);
				ChartUtilities.saveChartAsJPEG(file, chart, width, height);
			} catch (Exception e) {

			}
		}
	}
}
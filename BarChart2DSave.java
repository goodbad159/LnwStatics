import java.io.*;

import javax.swing.JFileChooser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;

public class BarChart2DSave {
	public BarChart2DSave(double cs,double cls,double ncs){

		final String comeStudents = "นักเรียนที่เข้าเรียน";
		final String comeLateStudents = "นักเรียนที่มาสาย";
		final String notcomeStudents = "นักเรียนที่ขาดเรียน";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(ncs, notcomeStudents, "");
		dataset.addValue(cls, comeLateStudents, "");
		dataset.addValue(cs, comeStudents, "");

		JFreeChart barChart = ChartFactory.createBarChart("เปอร์เซนต์ของนักเรียนในการเข้าร่วมกิจกรรม",
				"สถานะการเข้าร่วม", "เปอร์เซนต์", dataset, PlotOrientation.VERTICAL, true, true, false);

		int width = 640; /* Width of the image */
		int height = 480; /* Height of the image */
		JFileChooser ch = new JFileChooser();
		int op = ch.showSaveDialog(null);
		File BarChart;
		if(op==JFileChooser.APPROVE_OPTION){
			BarChart = ch.getSelectedFile();
			try {
				String BarChart2d = BarChart.getAbsolutePath()+".jpeg";
				File file = new File(BarChart2d);
				ChartUtilities.saveChartAsJPEG(file, barChart, width, height);
			} catch (Exception e) {
				
			}
		}
	}
}
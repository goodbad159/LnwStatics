import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarChart3DShow extends ApplicationFrame {
	private double cs;
	private double cls;
	private double ncs;

	public BarChart3DShow(double cs, double cls, double ncs) {
		super("กราฟแท่ง 3D");
		this.cs=cs;
		this.cls=cls;
		this.ncs=ncs;
		JFreeChart barChart = ChartFactory.createBarChart3D("เปอร์เซนต์ของนักเรียนในการเข้าร่วมกิจกรรม",
				"สถานะการเข้าร่วม", "เปอร์เซนต์", createDataset(), PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
	}

	private CategoryDataset createDataset() {
		final String comeStudents = "นักเรียนที่เข้าเรียน";
		final String comeLateStudents = "นักเรียนที่มาสาย";
		final String notcomeStudents = "นักเรียนที่ขาดเรียน";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(ncs, notcomeStudents, "");
		dataset.addValue(cls, comeLateStudents, "");
		dataset.addValue(cs, comeStudents, "");

		return dataset;
	}

}